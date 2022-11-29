package com.example.andoirdduan.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
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
import java.util.List;

public class DSSPActivity extends AppCompatActivity {
    SanPhamAdapter adapter;
    ListView listView;
    List<SanPham> listSP;
    ImageView imgSP;
    int REQUEST_CODE=123;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.dssp_activity );
        listView = (ListView) findViewById(R.id.lvSanPham);
        ImageView imgSP = findViewById(R.id.imageView_sua);
        loadData();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                SanPham sp = listSP.get(i);
                final String maSP = sp.getMaSP();
                final AlertDialog.Builder builder = new AlertDialog.Builder( DSSPActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn có muốn xóa khóa học này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        LoadingScreenActivity.db.TruyVan("DELETE FROM SanPham WHERE ID='" + maSP + "'");
                        Toast.makeText( DSSPActivity.this, "Đã Xóa "+maSP, Toast.LENGTH_SHORT).show();
                        getDATA();
                    }
                });
                builder.setNeutralButton( "Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Dialog dialog1 = new Dialog( DSSPActivity.this, android.R.style.Theme_Material_Light_Panel);
                        dialog1.requestWindowFeature( Window.FEATURE_NO_TITLE);
                        dialog1.setContentView(R.layout.sanpham_sua);
                        dialog1.setCanceledOnTouchOutside(false);
                        dialog1.show();

                        final EditText edMaSP =  dialog1.findViewById(R.id.edMaSP_sua);
                        final EditText edTenSP =  dialog1.findViewById(R.id.edTenSP_sua);
                        final EditText edSoLuong = dialog1.findViewById(R.id.edSoLuong_sua);
                        final EditText edMota = dialog1.findViewById(R.id.edMota_sua);
                        final EditText edPhanLoai = dialog1.findViewById(R.id.edPhanLoai_sua);
                        final EditText edGiaTien =  dialog1.findViewById(R.id.edGiaTien_sua);
                        ImageView imgSP1 = dialog1.findViewById(R.id.imageView_sua);


                        Button btnXacNhan = dialog1.findViewById(R.id.btnThemSP_sua);
                        Button btnHuy = dialog1.findViewById(R.id.btnXemDS_sua);
                        final String ma = listSP.get( i ).getMaSP();
                        final String ten = listSP.get(i).getTenSP();
                        final String mota = listSP.get(i).getMoTa();
                        final int giaTien = listSP.get(i).getGia();
                        final int soLuong = listSP.get(i).getSoLuong();
                        final String phanLoai = listSP.get(i).getPhanLoai();

                        edMaSP.setText( ma );
                        edTenSP.setText(ten);
                        edMota.setText(mota);
                        edGiaTien.setText(String.valueOf( giaTien ));
                        edSoLuong.setText(String.valueOf( soLuong ));
                        edPhanLoai.setText(phanLoai);

                        Bitmap bitmap= BitmapFactory.decodeByteArray( listSP.get(i).getHinh(), 0,  listSP.get(i).getHinh().length);
                        imgSP1.setImageBitmap(bitmap);
                        imgSP1.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent =new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,REQUEST_CODE);
                            }
                        } );
                        btnXacNhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String maSP = ma;
                                String ten = edTenSP.getText().toString().trim();
                                String mota = edMota.getText().toString().trim();
                                String giaTien = edGiaTien.getText().toString().trim();
                                String phanLoai = edPhanLoai.getText().toString().trim();
                                String soLuong = edSoLuong.getText().toString().trim();

                                LoadingScreenActivity.db.TruyVan("UPDATE SanPham SET theLoai = '" + phanLoai + "',soLuong = '" + soLuong + "',tenSP = '" + ten + "',giaTien = '" + giaTien + "'," +
                                        "moTa = '" + mota + "',hinhAnh = '" + ConverttoArrayByte(imgSP1) +"' WHERE ID = '" + maSP + "'");
                                Toast.makeText( DSSPActivity.this, "Đã Sửa ", Toast.LENGTH_SHORT).show();
                                getDATA();
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
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return false;
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

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals( "" )){
                    loadData();
                }else{
                    Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham where tenSP like '%" + s + "%'");
                    listSP = new ArrayList<SanPham>();
                    while (cursor.moveToNext()) {
                        listSP.add(new SanPham(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getBlob(6)));
                    }

                    adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, listSP);
                    listView.setAdapter(adapter);
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }

    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        listSP = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            listSP.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6)));
        }

        adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, listSP);
        listView.setAdapter(adapter);
    }
    public void getDATA() {
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        listSP = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            listSP.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6)));
        }
        adapter = new SanPhamAdapter( DSSPActivity.this, R.layout.row__listview, listSP);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == REQUEST_CODE) {
                Bitmap photo = (Bitmap) data.getExtras().get( "data" );
                imgSP.setImageBitmap( photo );
            }
        }
        super.onActivityResult( requestCode, resultCode, data );
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