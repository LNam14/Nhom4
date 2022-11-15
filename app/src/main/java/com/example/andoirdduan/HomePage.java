package com.example.andoirdduan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.SanPham.SQL;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    SanPhamActivity sanPhamActivity;
    FloatingActionButton btnThem;
    Button C;
    ArrayList<SanPham> arraySanPham;
    GridView gridView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_activity );
        btnThem = findViewById( R.id.btnThem_HomePage );
        C = findViewById(R.id.C);
        gridView = findViewById( R.id.gvSanPham );
        loadData();
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
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getBlob(6)));
        }
        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, R.layout.cardview_activity, arraySanPham);
        gridView.setAdapter(adapter);
    }
}
