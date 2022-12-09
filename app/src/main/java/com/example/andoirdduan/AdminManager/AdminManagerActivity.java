package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;
import com.example.andoirdduan.UserManager.EditUserActivity;

public class AdminManagerActivity extends AppCompatActivity {
    TextView edNameAdmin,edQuyenAdmin,btnEditUserManager,btnDangXuatAdmin;
    String account ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        edNameAdmin = findViewById(R.id.edNameAdmin);
        edQuyenAdmin = findViewById(R.id.edQuyenAdmin);
        btnEditUserManager = findViewById(R.id.btnUserEditManager);
        btnDangXuatAdmin = findViewById(R.id.btnDangXuatAdmin);
        final SQLSever sqlSever = new SQLSever(this);

        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");

        edNameAdmin.setText(account);
        btnEditUserManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserEditManagerActivity.class);
                intent.putExtra("name",account);
                startActivity(intent);
            }
        });
        btnDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }



}