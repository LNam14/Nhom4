package com.example.andoirdduan.GioHang;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.Database.SQLSeverGioHang;
import com.example.andoirdduan.HoaDon.HoaDonActivity;
import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.Home.HomePageAdapter;
import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.Home.UserActivityAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamAdapter;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGioHang;
    String strUsername = "";
    ArrayList<GioHang> arraySanPham_gioHang;
    private SQLiteDatabase db;
    String nhoMk = "mua";
    TextView tvTongTien;
    Button btnMuaHang;
    int soLuong = 0;
    int daBan = 0;
    int i;
    String maSP = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView btnBack = findViewById( R.id.btnBack );
        Bundle bundle = getIntent().getExtras();
        if(bundle !=  null){
            strUsername = bundle.getString("dulieu");
            maSP = bundle.getString("maSP");
            soLuong = bundle.getInt( "soLuong" );
            daBan = bundle.getInt( "daBan" );
        }
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, UserActivity.class);
                startActivity( intent );
            }
        } );

        setContentView( R.layout.activity_gio_hang );
        lvGioHang = findViewById( R.id.lvGioHang );
        tvTongTien =findViewById( R.id.tvTongTien );
        btnMuaHang = findViewById( R.id.btnMuaHang );
        btnMuaHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplicationContext(), HoaDonActivity.class );
                    intent.putExtra("soLuong", soLuong);
                    intent.putExtra("maSP", maSP);
                    intent.putExtra( "dulieu",strUsername );
                    intent.putExtra("daBan", daBan);
                    intent.putExtra( "mua",arraySanPham_gioHang.get( i ).getSoLuong() );
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText( GioHangActivity.this, "Vui lòng thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        getDoanhThu();
        loadData();
        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_giohang, arraySanPham_gioHang);
        lvGioHang.setAdapter(adapter);


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
        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_giohang, arraySanPham_gioHang);
        lvGioHang.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(nhoMk,MODE_PRIVATE);
        String tien = sharedPreferences.getString("Username", "");
        boolean save = sharedPreferences.getBoolean("Save", false);
        if(save ==true){
            tvTongTien.setText(tien);
        }
    }
    public double getDoanhThu(){
        int doanhThu = 0;
        Cursor data = LoadingScreenActivity.db.TruyVanTraVe( "SELECT SUM(soLuong*giaTien) FROM GioHang where user = '" +strUsername+"'");
        while (data.moveToNext()) {
            int tongTien = data.getInt( 0);
            doanhThu += tongTien ;
        }
        tvTongTien.setText(doanhThu+"$");
        return doanhThu;
    }
}