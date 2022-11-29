package com.example.andoirdduan.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.DBUser.User;
import com.example.andoirdduan.R;
import com.example.andoirdduan.Database.SQLSever;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    Button btnDangKi,imageButtonNgaySinh;
    EditText edTaiKhoang,edMatKhau,edEmail,edTen,edNgaySinh;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        edTaiKhoang = findViewById(R.id.edAccount_RegisterActivity);
        edEmail = findViewById(R.id.edMail_RegisterActivity);
        edMatKhau = findViewById(R.id.edPassword_RegisterActivity);
        edTen = findViewById(R.id.edTen_RegisterActivity);
        edNgaySinh = findViewById(R.id.edNgaySinh_RegisterActivity);
        btnDangKi = findViewById(R.id.btnRegister_RegisterActivity);
        login = findViewById(R.id.tvLogin_RegisterActivity);
        final SQLSever sqlSever = new SQLSever(this);
        final SQLSever sql_user = new SQLSever(this);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String acc= edTaiKhoang.getText().toString();
                final String Gmail = edEmail.getText().toString();
                final String mk1= edMatKhau.getText().toString();
                final String ten= edTen.getText().toString();
                final String ngaysinh= edNgaySinh.getText().toString();
                if(Gmail.equals("")|| mk1.equals("") || acc.equals("") || ten.equals("") || ngaysinh.equals("")){
                    Toast.makeText(RegisterActivity.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<User> users= sqlSever.getArrayUser();
                    System.out.println("UserArray"+users);
                        boolean ketqua = true;
//                        for(User x : users){
//                            if(x.getAccount().equals(acc)){
//                                ketqua = false;
//                            }
//                        }
                        if(ketqua == true){
                            User s = new User(acc, Gmail, mk1, ten, ngaysinh);
                            sqlSever.AddUser(s);
                            if(s!=null){
                                Toast.makeText(RegisterActivity.this, "Đăng Ký Thành Công ^.^", Toast.LENGTH_SHORT).show();
                                edTaiKhoang.setText("");
                                edEmail.setText("");
                                edMatKhau.setText("");
                                edTen.setText("");
                                edNgaySinh.setText("");
                            }else{
                                Toast.makeText(RegisterActivity.this, "Đăng Ký Thất Bại ^.^", Toast.LENGTH_SHORT).show();
                                edTaiKhoang.setText("");
                                edEmail.setText("");
                                edMatKhau.setText("");
                                edTen.setText("");
                                edNgaySinh.setText("");
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Account Đã Tồn tại!!!", Toast.LENGTH_SHORT).show();
                        }

                }
            };

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });

    }
    public void OpenLogin(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}