package com.example.andoirdduan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andoirdduan.Database.DataBase;
import com.example.andoirdduan.HomePage;
import com.example.andoirdduan.R;

public class LoginActivity extends AppCompatActivity {
    Button DangNhap;
    TextView DangKi;
    public static DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.login_activity);

        //truy vấn database
        dataBase = new DataBase( LoginActivity.this, "SQLite.sqlite", null, 1 );
        dataBase.QueryData( "CREATE TABLE IF NOT EXISTS SanPham1(maSP varchar(10), theLoai varchar(20), nhaCungCap varchar(20),tenSP varchar(20), giaTien varchar(20), moTa varchar(30), hinhAnh BLOB)" );

        DangNhap = findViewById(R.id.btnRegister_RegisterActivity);
        DangKi = findViewById(R.id.tvRegister_LoginActivity);
        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });
        DangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}