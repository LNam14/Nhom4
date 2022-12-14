package com.example.andoirdduan.Login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.R;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    Button btnDangKi,imageButtonNgaySinh;
    EditText edTaiKhoang,edMatKhau,edEmail,edNgaySinh,edTen;
    TextView login;
    int vi = 0;
    ArrayList<User> users;
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
        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dateEvent = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int t = i1 + 1;
                edNgaySinh.setText(i2+"/"+t+"/"+i);
            }
        };
        edNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this, dateEvent,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE));
                dialog.show();
            }
        });


        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ngaySinh = edNgaySinh.getText().toString();
                final String acc= edTaiKhoang.getText().toString();
                final String Gmail = edEmail.getText().toString();
                final String mk1= edMatKhau.getText().toString();
                final String ten= edTen.getText().toString();
                if(Gmail.equals("")|| mk1.equals("") || acc.equals("") || ten.equals("") || ngaySinh.equals("")){
                    Toast.makeText(RegisterActivity.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else if(!isEmail(Gmail)){
                    edEmail.setError("Sai định dạng email");
                } else if(!sqlSever.ktraEmail(Gmail)){
                    edEmail.setError("Email đã tồn tại");
                }else{
                    ArrayList<User> users= sqlSever.getArrayUser();
                    System.out.println("UserArray"+users);
                        boolean ketqua = true;
                        if(ketqua == true){

                            User s = new User(acc, Gmail, mk1, ten, ngaySinh,vi);
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
    public Boolean isEmail(String str) {
        String pattern = "\\w+@\\w+(\\.\\w+){1,2}";
        return str.matches(pattern);
    }



}