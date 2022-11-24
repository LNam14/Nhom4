package com.example.andoirdduan.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserActivity extends AppCompatActivity {
    FloatingActionButton gioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        gioHang = findViewById(R.id.fab);

        gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });


    }
}