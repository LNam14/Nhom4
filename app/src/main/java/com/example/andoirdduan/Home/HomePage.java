package com.example.andoirdduan.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    TextView tvUserName;
    FloatingActionButton btnThem;
    ArrayList<SanPham> arraySanPham;
    GridView gridView;
    BottomNavigationView navigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.homepage_activity );
        btnThem = findViewById( R.id.btnThem_HomePage );
        gridView = findViewById( R.id.gvSanPham );
        navigationView = findViewById( R.id.bottomNavigationView );
        tvUserName = findViewById( R.id.tvUserName);
        tvUserName.setText( "Họ tên" );
        loadData();
        navigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getBaseContext(), HomePage.class);
                        startActivity(home);
                        break;
                    case R.id.search:
                        break;
                    case R.id.insert:
                        Intent insert = new Intent(getBaseContext(), SanPhamActivity.class);
                        startActivity(insert);
                        break;
                }
                return false;
            }
        });
        btnThem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SanPhamActivity.class);
                startActivity(intent);
            }
        } );

    }
    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6)));
        }
//        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, R.layout.cardview_activity, arraySanPham);
        HomePageAdapter adapter = new HomePageAdapter(HomePage.this,R.layout.cardview_activity, arraySanPham);
        gridView.setAdapter(adapter);
    }
    public void chuyenTrang(){
        Intent intent = new Intent(getBaseContext(), DSSPActivity.class );
        startActivity( intent );
    }
}
