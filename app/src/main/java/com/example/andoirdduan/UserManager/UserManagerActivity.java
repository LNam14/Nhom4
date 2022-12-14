package com.example.andoirdduan.UserManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.CTHD.LichSuHoaDon;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.GioHang.GioHangAdapter;
import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagerActivity extends AppCompatActivity {
    TextView Name,Quyen,Vi,NapThe,DangXuat,btnTaiKhoan,btnHoaDon,btnRut;
    String strUsername = "";
    int Tien = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManagerActivity.this, UserActivity.class);
                startActivity( intent );
            }
        } );
        Name = findViewById(R.id.edNameUser);
        Quyen = findViewById(R.id.edQuyen);
        NapThe = findViewById(R.id.btnNap);
        DangXuat = findViewById(R.id.btnDangXuat);
        btnRut = findViewById( R.id.btnRut );
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        btnHoaDon = findViewById( R.id.btnHoaDonn );
        Vi = findViewById(R.id.edViTien);
        final SQLSever sqlSever = new SQLSever(this);
        Bundle bundle1 = getIntent().getExtras();
        strUsername = bundle1.getString("name_user");
        System.out.println("Name: "+strUsername);
        User s = sqlSever.getUser(strUsername);
        Name.setText(s.getAccount());
        Quyen.setText("User");
        Vi.setText(""+s.getVi()+"đ");
        btnRut.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tien = s.getVi();
                Intent intent = new Intent(getApplicationContext(), RutTienActivity.class);
                intent.putExtra("name_user", strUsername);
                intent.putExtra("Tien", Tien);
                startActivity(intent);
            }
        } );
        btnHoaDon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LichSuHoaDon.class);
                intent.putExtra("dulieu", strUsername);
                startActivity( intent );
            }
        } );
        NapThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tien = s.getVi();
                Intent intent = new Intent(getApplicationContext(), NapTienActivity.class);
                intent.putExtra("name_user", strUsername);
                intent.putExtra("Tien", Tien);
                startActivity(intent);
//                Toast.makeText( UserManagerActivity.this, "Name"+strUsername, Toast.LENGTH_SHORT ).show();
            }
        });
        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
                intent.putExtra("name",strUsername);
                startActivity(intent);
            }
        });
        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("ckLogin", false);
                startActivity(intent);
            }
        });

    }



}