package com.example.andoirdduan.HoaDon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.CTHD.LichSu;
import com.example.andoirdduan.CTHD.LichSuHoaDon;
import com.example.andoirdduan.ChiTietHoaDon.CTHoaDon;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.DiaChiNhanHang.DiaChiAdapter;
import com.example.andoirdduan.DiaChiNhanHang.DiaChiNhanHangActivity;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamAdapter;
import com.example.andoirdduan.User;
import com.example.andoirdduan.UserManager.UserManagerActivity;

import java.util.ArrayList;

public class HoaDonActivity extends AppCompatActivity {
    TextView tvHoTen,tvTongTienHoaDon,tvSDT,tvTHX,tvSoNha;
    ListView lvSanPhamThanhToan,lvDCHD;
    String strUsername = "";
    ArrayList<GioHang> arraySanPham_gioHang;
    ArrayList<DiaChi> list;
    ArrayList<LichSu> listHD;
    ArrayList<GioHang> listGH;
    ArrayList<SanPham> listSP;
    DiaChi dc = new DiaChi();
    ImageButton btnTheMDC;
    Button btnMuaHangHoaDon;
    int i;
    int doanhThu = 0;
    int Tien = 0;
    int tienConLai = 0;
    int soLuong = 0;
    int soLuongAll = 0;
    int daBan = 0;
    String maSP = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_xacnhan );
        setContentView(R.layout.activity_hoa_don);
        btnTheMDC = findViewById( R.id.btnTheMDC );
        lvSanPhamThanhToan = findViewById( R.id.lvSanPhamThanhToan );
        lvDCHD = findViewById( R.id.lvDCHD );
        tvTongTienHoaDon = findViewById( R.id.tvTongTienHoaDon );
        btnMuaHangHoaDon = findViewById( R.id.btnMuaHangHoaDon );
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 !=  null){
            strUsername = bundle1.getString("dulieu");
            maSP = bundle1.getString( "maSP" );
            soLuong = bundle1.getInt( "mua" );
            soLuongAll = bundle1.getInt( "soLuong" );
            daBan = bundle1.getInt( "daBan" );
            Toast.makeText( this, "ma"+maSP+"so"+soLuongAll+"u"+strUsername, Toast.LENGTH_SHORT ).show();
        }
        loadData();
        getDoanhThu();
        loadData1();
        btnMuaHangHoaDon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                final SQLSever sqlSever = new SQLSever(getBaseContext());
                SanPham sanPham = (SanPham) getIntent().getSerializableExtra("chitiet");
                DiaChi dc = LoadingScreenActivity.db.getDC();
                User s = sqlSever.getUser(strUsername);
                Tien = s.getVi();
                tienConLai = Tien - doanhThu;
                if(Tien>doanhThu){
                    if (sqlSever.updateNap(strUsername, String.valueOf(tienConLai)) > 0) {
                        if((soLuongAll-soLuong)>=0){
                                Toast.makeText(getApplicationContext(), "Mua thành công"+soLuong, Toast.LENGTH_SHORT).show();
                                LoadingScreenActivity.db.InsertHD(dc.getHoTen(),dc.getSdt(),dc.getTHX(),dc.getSoNha(),doanhThu,strUsername);
                                loadDataHD();
                                LoadingScreenActivity.db.TruyVan( "Update SanPham Set daBan = '"+ (soLuong+daBan) +"',soLuong = '"+(soLuongAll-soLuong)  +"'  where ID = '"+maSP+"'" );
                                loadDataSP();
                                LoadingScreenActivity.db.TruyVan( "DELETE FROM GioHang where user = '"+strUsername+"'" );
                                loadData();
                                Intent intent = new Intent(getApplicationContext(), LichSuHoaDon.class );
                                intent.putExtra("dulieu", strUsername);
                                startActivity(intent);
                        }else{
                            Toast.makeText( HoaDonActivity.this, "Sản phẩm không đđủ", Toast.LENGTH_SHORT ).show();
                        }
                    }
                    Toast.makeText( HoaDonActivity.this, "đủ tiền" ,Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( HoaDonActivity.this, "k đủ tiền", Toast.LENGTH_SHORT ).show();
                }
                }catch (Exception ex){
                    Toast.makeText( HoaDonActivity.this, "Vui lòng thêm địa chỉ", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        btnTheMDC.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DiaChiNhanHangActivity.class );
                intent.putExtra("dulieu", strUsername);
                startActivity( intent );
            }
        } );
    }
    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang where user ='" + strUsername + "'" );
        arraySanPham_gioHang = new ArrayList<GioHang>();
        while (cursor.moveToNext()) {
            arraySanPham_gioHang.add( new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 )) );
        }
        HoaDonAdapter adapter = new HoaDonAdapter( HoaDonActivity.this,R.layout.hoa_don_row_activity, arraySanPham_gioHang);
        lvSanPhamThanhToan.setAdapter(adapter);
    }

    public double getDoanhThu(){
        Cursor data = LoadingScreenActivity.db.TruyVanTraVe( "SELECT SUM(soLuong*giaTien) FROM GioHang where user ='" + strUsername + "'" );
        while (data.moveToNext()) {
            int tongTien = data.getInt( 0);
            doanhThu += tongTien ;
            tvTongTienHoaDon.setText( doanhThu+"$" );
        }
        return doanhThu;
    }
    public void loadData1(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi where user ='" + strUsername + "'" );
        list = new ArrayList<DiaChi>();
        while (cursor.moveToNext()) {
            list.add( new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 )));
        }
        DiaChiAdapter adapter = new DiaChiAdapter( HoaDonActivity.this,R.layout.row_giohang, list);
        lvDCHD.setAdapter(adapter);
    }
    public void loadDataHD() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from HoaDon where user = '"+strUsername+"'" );
        listHD = new ArrayList<LichSu>();
        while (cursor.moveToNext()) {
            listHD.add( new LichSu(
                    cursor.getInt( 0 ),
                    cursor.getString( 1 ),
                    cursor.getInt( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 )));
        }
    }
    public void loadDataSP(){
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
                    cursor.getBlob(6),
                    cursor.getInt( 7 )));
        }

    }
}