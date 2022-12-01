package com.example.andoirdduan.Register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.LoadingScreenActivity;
import com.example.andoirdduan.R;
public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edPassword, edHoten, edDate, edEmail;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.register_activity );
        edUsername = findViewById( R.id.edAccount_RegisterActivity );
        edPassword = findViewById( R.id.edPassword_RegisterActivity );
        edHoten = findViewById( R.id.edTen_RegisterActivity );
        edDate = findViewById( R.id.edNgaySinh_RegisterActivity );
        edEmail = findViewById( R.id.edMail_RegisterActivity );

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String hoten = edHoten.getText().toString().trim();
                String date = edDate.getText().toString().trim();
                LoadingScreenActivity.db.User(username,email,password,hoten,date);
                Toast.makeText( RegisterActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
}