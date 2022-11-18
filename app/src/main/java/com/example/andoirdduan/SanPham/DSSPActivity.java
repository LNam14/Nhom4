package com.example.andoirdduan.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DSSPActivity extends AppCompatActivity {
    SanPhamAdapter adapter;
    ListView listView;
    ArrayList<SanPham> arraySanPham;
    public static int animationitem;
    String theLoai,tenSP,moTa,nhaCC;
    int gia;
    byte[] imgSP;
    int position1;
    ImageView imgSP1;
    int REQUEST_CODE=123;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.dssp_activity );
        listView = (ListView) findViewById(R.id.lvSanPham);
        final ImageView imageView = findViewById( R.id.imageView_sua );
        loadData();
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String maSP = arraySanPham.get(i).getTenSP();
                position1 = i;
                final AlertDialog.Builder builder = new AlertDialog.Builder( DSSPActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn có muốn xóa khóa học này không?");
                builder.setNeutralButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoadingScreenActivity.db.TruyVan("DELETE FROM SanPham WHERE tenSP ='" + maSP + "'");
                        Toast.makeText( DSSPActivity.this, "Đã Xóa ", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                });
                builder.setNegativeButton( "Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Dialog dialog1 = new Dialog( DSSPActivity.this);
                        dialog1.requestWindowFeature( Window.FEATURE_NO_TITLE);
                        dialog1.setContentView(R.layout.sanpham_sua);
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.show();

                        final EditText edTenSP =  dialog1.findViewById(R.id.edTenSP_sua);
                        final EditText edPhanLoai = dialog1.findViewById(R.id.edPhanLoai_sua);
                        final EditText edMota = dialog1.findViewById(R.id.edMota_sua);
                        final EditText edNhaCC = dialog1.findViewById(R.id.edNhaCC_sua);
                        final EditText edGiaTien =  dialog1.findViewById(R.id.edGiaTien_sua);


                        Button btnXacNhan = dialog1.findViewById(R.id.btnThemSP_sua);
                        Button btnHuy = dialog1.findViewById(R.id.btnXemDS_sua);

                        final String ma = arraySanPham.get(i).getMaSP();
                        final String ten = arraySanPham.get(i).getTenSP();
                        final String mota = arraySanPham.get(i).getMoTa();
                        final int giaTien = arraySanPham.get(i).getGia();
                        final String nhaCC = arraySanPham.get(i).getNhaCC();
                        final String phanLoai = arraySanPham.get(i).getNhaCC();


                        edTenSP.setText(ten);
                        edMota.setText(mota);
                        edGiaTien.setText(giaTien);
                        edNhaCC.setText(nhaCC);
                        edPhanLoai.setText(phanLoai);

                        btnXacNhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String MaGV = ma;
                                String ten = edTenSP.getText().toString().trim();
                                String mota = edMota.getText().toString().trim();
                                int giaTien = Integer.parseInt( edGiaTien.getText().toString().trim() );
                                String phanLoai = edPhanLoai.getText().toString().trim();
                                String nhaCC = edNhaCC.getText().toString().trim();

                                LoadingScreenActivity.db.TruyVanTraVe("UPDATE SanPham SET theLoai = '" + phanLoai + "',nhaCungCap = '" + nhaCC + "',tenSP = '" + ten + "',giaTien = '" + giaTien + "'," +
                                        "moTa = '" + mota + "' WHERE maGV= '" + MaGV + "'");
                                Toast.makeText( DSSPActivity.this, "Đã Sửa ", Toast.LENGTH_SHORT).show();getDATA();
                                dialog1.dismiss();
                            }
                        });
                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search, menu );
        SearchView searchView = (SearchView) menu.findItem( R.id.search ).getActionView();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.Search( s );
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals( "" )){
                    loadData();
                }else{
                    Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham Where tenSP = '" + s + "'");
                    arraySanPham = new ArrayList<SanPham>();
                    while (cursor.moveToNext()) {
                        arraySanPham.add(new SanPham(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getBlob(6)));
                    }

                    adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, arraySanPham);
                    listView.setAdapter(adapter);
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }

    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6)));
        }

        adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, arraySanPham);
        listView.setAdapter(adapter);
    }
    public void getDATA() {
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            String maSP = cursor.getString(0);
            String tenSP = cursor.getString(1);
            String dateBD = cursor.getString(2);
            String dateKT = cursor.getString(3);
            int gia = cursor.getInt(3);
            String mota = cursor.getString(3);
            byte[] hinh = cursor.getBlob(3);
            arraySanPham.add(new SanPham(maSP, tenSP, dateBD, dateKT,gia,mota,hinh));
        }
        adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, arraySanPham);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSP1.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}