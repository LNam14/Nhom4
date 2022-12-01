package com.example.andoirdduan.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.R;
import com.example.andoirdduan.Database.DataBase;

public class LoadingScreenActivity extends AppCompatActivity {
    public static DataBase db;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.loading_screen_activity);

        db = new DataBase(this,"ShopGiay.sqlite",null,1);
        db.TruyVan("Create Table If not Exists SanPham(ID Text Primary Key, tenSP Text, theLoai Text,soLuong INTEGER, giaTien INTEGER, moTa Text, hinhAnh Blob,daBan INTEGER)");
        db.TruyVan("Create Table If not Exists GioHang(ID Text , tenSP Text, theLoai Text,soLuong INTEGER, giaTien INTEGER, moTa Text, hinhAnh Blob, user Text)");
        db.TruyVan("Create Table If not Exists TongTien(tien Integer )");
        add = findViewById(R.id.btnRegister_RegisterActivity);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}