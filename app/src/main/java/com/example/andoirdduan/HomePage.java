package com.example.andoirdduan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.SanPham.SQL;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    SanPhamActivity sanPhamActivity;
    FloatingActionButton btnThem;
    Button C;
    ArrayList<SanPham> arraySanPham;
    GridView gridView;
    BottomNavigationView navigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_activity );
        btnThem = findViewById( R.id.btnThem_HomePage );
        C = findViewById(R.id.C);
        gridView = findViewById( R.id.gvSanPham );
        navigationView = findViewById( R.id.bottomNavigationView );


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
        C.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6)));
        }
        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, R.layout.cardview_activity, arraySanPham);
        gridView.setAdapter(adapter);
    }
}
