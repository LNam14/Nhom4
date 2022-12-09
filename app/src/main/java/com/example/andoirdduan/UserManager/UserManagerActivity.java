package com.example.andoirdduan.UserManager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.GioHang.GioHangAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagerActivity extends AppCompatActivity {
    TextView Name,Quyen,Vi,NapThe,DangXuat,btnTaiKhoan;
    String account = "";
    int Tien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        Name = findViewById(R.id.edNameUser);
        Quyen = findViewById(R.id.edQuyen);
        NapThe = findViewById(R.id.btnNap);
        DangXuat = findViewById(R.id.btnDangXuat);
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        Vi = findViewById(R.id.edViTien);
        final SQLSever sqlSever = new SQLSever(this);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");
        Toast.makeText(this, "Name: " +account, Toast.LENGTH_SHORT).show();
        System.out.println("Name: "+account);
        User s = sqlSever.getUser(account);
        Name.setText(s.getAccount());
        Quyen.setText("User");
        Vi.setText(""+s.getVi()+"đ");


        NapThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tien = s.getVi();
                Intent intent = new Intent(getApplicationContext(), NapTienActivity.class);
                intent.putExtra("name_user", account);
                intent.putExtra("Tien", Tien);
                startActivity(intent);
            }
        });
        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
                intent.putExtra("name",account);
                startActivity(intent);
            }
        });
        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }



}