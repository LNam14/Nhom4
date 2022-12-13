package com.example.andoirdduan.Login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.R;
import com.example.andoirdduan.Database.DataBase;

import java.io.ByteArrayOutputStream;

public class LoadingScreenActivity extends AppCompatActivity {
    public static DataBase db;
    Button add;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.loading_screen_activity);
        db = new DataBase(this,"ShopGiay.sqlite",null,1);
        db.TruyVan("Create Table If not Exists SanPham(ID Text Primary Key, tenSP Text, theLoai Text,soLuong INTEGER, giaTien INTEGER, moTa Text, hinhAnh Blob,daBan INTEGER)");
        db.TruyVan("Create Table If not Exists GioHang(ID Text , tenSP Text, theLoai Text,size Text,soLuong INTEGER, giaTien INTEGER, moTa Text, hinhAnh Blob, user Text)");
        db.TruyVan("Create Table If not Exists GioHang1(ID Text , tenSP Text, theLoai Text,size Text,soLuong INTEGER, giaTien INTEGER, moTa Text, hinhAnh Blob, user Text)");
        db.TruyVan("Create Table If not Exists DiaChi(hoTen Text, sdt Integer, THX Text, soNha Text, user Text)");
        db.TruyVan("Create Table If not Exists DiaChi1(hoTen Text, sdt Integer, THX Text, soNha Text, user Text)");
        db.TruyVan("Create Table If not Exists NapTien(user Text, date Text, money INTEGER, trangThai Text)");
        db.TruyVan("Create Table If not Exists RutTien(user Text, date Text, money INTEGER)");
        db.TruyVan("Create Table If not Exists YeuCau(ID Integer Primary Key Autoincrement, user Text, date Text, money INTEGER, phanUng Text)");
        db.TruyVan("Create Table If not Exists HoaDon(ID Integer Primary Key Autoincrement, hoTen Text, sdt INTEGER, diaChi Text,soNha Text, tongTien INTEGER, user Text)");
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