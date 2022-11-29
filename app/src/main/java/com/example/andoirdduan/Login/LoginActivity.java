package com.example.andoirdduan.Login;

import static android.content.Intent.EXTRA_USER;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.DBUser.User;
import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.Database.SQLSever;

public class LoginActivity extends AppCompatActivity {
    Button DangNhap;
    TextView DangKi;
    TextView TenTaiKhoang, MatKhau;
    private CheckBox ckNhoMatKhau;
    private Boolean saveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        DangNhap = findViewById(R.id.btnRegister_RegisterActivity);
        DangKi = findViewById(R.id.tvRegister_LoginActivity);
        TenTaiKhoang = findViewById(R.id.edAccount_LoginActivity);
        MatKhau = findViewById(R.id.edPassword_RegisterActivity);
        ckNhoMatKhau = findViewById(R.id.ckNhoMatKhau);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        saveLogin = sharedPreferences.getBoolean("REMEMBER", false);
        if(saveLogin == true){
            TenTaiKhoang.setText(sharedPreferences.getString("USERNAME", ""));
            MatKhau.setText(sharedPreferences.getString("PASSWORD", ""));
            System.out.println("USER: "+TenTaiKhoang);
            System.out.println("PASS: "+MatKhau);
            ckNhoMatKhau.setChecked(true);
        }
        final SQLSever sqlSever = new SQLSever(this);
//        User s = new User("nguyensama", "vannguyenhv201@gmail.com", "Nguyendz1", "Nguyễn Văn Nguyên", "24/03/2003");
//        sqlSever.AddUser(s);

        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = TenTaiKhoang.getText().toString();
                String pass = MatKhau.getText().toString();
                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else if(name.equals("admin") && pass.equals("123")){
                    rememberMe(name, pass, ckNhoMatKhau.isChecked());
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    intent.putExtra("hoten", TenTaiKhoang.getText().toString());
                    startActivity(intent);
                } else {
                    User s = sqlSever.getUser(name);
                    if (s != null) {
                        if (s.getPassword().equals(pass)) {
                            rememberMe(name, pass, ckNhoMatKhau.isChecked());
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công ^.^", Toast.LENGTH_SHORT).show();
                            Login(s);

                        } else {
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
//                ckNhoMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                    }
//                })
                //cmámcmámmmmmmmmmmm
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


        public void rememberMe(String username, String pass, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
        }


        public void Login (User s){
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            intent.putExtra("hoten", TenTaiKhoang.getText().toString());
            startActivity(intent);
            finish();
    }
}
