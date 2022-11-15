package com.example.andoirdduan.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SQL;

public class LoadingScreenActivity extends AppCompatActivity {
    public static SQL db;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.loading_screen_activity);

        db = new SQL(this,"ShopGiay.sqlite",null,1);
        db.TruyVan("Create Table If not Exists SanPham(ID Varchar Primary Key, theLoai Varchar, nhaCungCap Varchar,tenSP Varchar, giaTien Varchar, moTa Varchar, hinhAnh Blob)");
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