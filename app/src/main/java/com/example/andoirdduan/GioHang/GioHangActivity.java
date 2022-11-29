package com.example.andoirdduan.GioHang;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.Database.SQLSeverGioHang;
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

public class GioHangActivity extends AppCompatActivity {
    SQLSeverGioHang sqlSeverGioHang ;
    ListView lvGioHang;
    ArrayList<GioHang> arraySanPham_gioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        setContentView( R.layout.activity_gio_hang );
        lvGioHang = findViewById( R.id.lvGioHang );

        loadData();

    }
    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GIOHANG" );
        arraySanPham_gioHang = new ArrayList<GioHang>();
        while (cursor.moveToNext()) {
            arraySanPham_gioHang.add( new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getInt( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getString( 5 ),
                    cursor.getBlob( 6 ) ) );
        }
        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_gio_hang, arraySanPham_gioHang);
        lvGioHang.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.back, menu );
        Button back = (Button) menu.findItem( R.id.back ).getActionView();
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, UserActivity.class );
                startActivity( intent );
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }
}