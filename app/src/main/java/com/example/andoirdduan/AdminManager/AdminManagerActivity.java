package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPhamActivity;
import com.example.andoirdduan.User;
import com.example.andoirdduan.UserManager.EditUserActivity;

public class AdminManagerActivity extends AppCompatActivity {
    TextView edNameAdmin,edQuyenAdmin,btnEditUserManager,btnDangXuatAdmin,btnTaiKhoangAdmin;
    String account ="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        edNameAdmin = findViewById(R.id.edNameAdmin);
        edQuyenAdmin = findViewById(R.id.edQuyenAdmin);
        btnTaiKhoangAdmin = findViewById(R.id.btnTaiKhoanAdmin);
        btnEditUserManager = findViewById(R.id.btnUserEditManager);
        btnDangXuatAdmin = findViewById(R.id.btnDangXuatAdmin);
        final SQLSever sqlSever = new SQLSever(this);

        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminManagerActivity.this, HomePage.class);
//                intent.putExtra("name_user", account);
                startActivity( intent );
            }
        } );
        edNameAdmin.setText(account);
        btnEditUserManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserEditManagerActivity.class);
                intent.putExtra("name",account);
                startActivity(intent);
            }
        });
        btnTaiKhoangAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home1 = new Intent(getBaseContext(), XacNhanNapTien.class);
                home1.putExtra("name",account);
                startActivity(home1);
            }
        });
        btnDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("ckLogin", false);
                startActivity(intent);
            }
        });

    }



}