package com.example.andoirdduan.HoaDon;

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
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.User;
import com.example.andoirdduan.UserManager.UserManagerActivity;

import java.util.ArrayList;

public class HoaDonActivity extends AppCompatActivity {
    TextView tvHoTen,tvTongTienHoaDon,tvSDT,tvTHX,tvSoNha;
    ListView lvSanPhamThanhToan,lvDCHD;
    String strUsername = "";
    ArrayList<GioHang> arraySanPham_gioHang;
    ArrayList<DiaChi> list;
    DiaChi dc = new DiaChi();
    ImageButton btnTheMDC;
    Button btnMuaHangHoaDon;
    int i;
    int doanhThu = 0;
    int Tien = 0;
    int tienConLai = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        btnTheMDC = findViewById( R.id.btnTheMDC );
        lvSanPhamThanhToan = findViewById( R.id.lvSanPhamThanhToan );
        lvDCHD = findViewById( R.id.lvDCHD );
        tvTongTienHoaDon = findViewById( R.id.tvTongTienHoaDon );
        btnMuaHangHoaDon = findViewById( R.id.btnMuaHangHoaDon );
        btnMuaHangHoaDon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SQLSever sqlSever = new SQLSever(getBaseContext());
                GioHang gh = LoadingScreenActivity.db.getGH();
                DiaChi dc = LoadingScreenActivity.db.getDC();
                User s = sqlSever.getUser(strUsername);
                Tien = s.getVi();
                tienConLai = Tien - doanhThu;
//                String tenSP = gh.getTenSP();
//                String size = gh.getSize();
//                int soLuong = gh.getSoLuong();
//                int donGia = gh.getGia();
//                String hoTen = dc.getHoTen();
//                int sdt = dc.getSdt();

                if(Tien>doanhThu){
                    if (sqlSever.updateNap(strUsername, String.valueOf(tienConLai)) > 0) {
                        Toast.makeText(getApplicationContext(), "Mua thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LichSuHoaDon.class );
                        startActivity(intent);
                    }
                    Toast.makeText( HoaDonActivity.this, "đủ tiền" ,Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( HoaDonActivity.this, "k đủ tiền", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 !=  null){
            strUsername = bundle1.getString("dulieu");
            Toast.makeText(this, "Name: " +strUsername, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Chua dc", Toast.LENGTH_SHORT).show();
        }
        loadData();
        getDoanhThu();
        loadData1();
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
    public void loadDataDC() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi1" );
        list = new ArrayList<DiaChi>();
        while (cursor.moveToNext()) {
            list.add( new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 )) );
        }
    }
    public double getDoanhThu(){

        Cursor data = LoadingScreenActivity.db.TruyVanTraVe( "SELECT SUM(soLuong*giaTien) FROM GioHang" );
        while (data.moveToNext()) {
            int tongTien = data.getInt( 0);
            doanhThu += tongTien ;
        }
        tvTongTienHoaDon.setText(doanhThu+"$");
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
}