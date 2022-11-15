package com.example.andoirdduan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;

public class RegisterActivity extends AppCompatActivity {
    Button btnDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.register_activity);
        btnDangKi = findViewById(R.id.btnRegister_RegisterActivity);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}