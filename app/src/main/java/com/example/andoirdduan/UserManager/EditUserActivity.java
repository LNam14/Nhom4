package com.example.andoirdduan.UserManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

public class EditUserActivity extends AppCompatActivity {
    EditText edMail,edTen,edNgaySinh,edNewPass,edPass;
    String account ="";
    SQLSever sqlSever;
    User user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        edMail = findViewById(R.id.edMail);
        edTen = findViewById(R.id.edTen);
        edNgaySinh = findViewById(R.id.edNgaySinh);
        edNewPass = findViewById(R.id.edNewPass);
        edPass = findViewById(R.id.edPass);
        sqlSever = new SQLSever(this);

        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name");

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserActivity.this, UserManagerActivity.class);
                intent.putExtra("name_user", account);
                startActivity( intent );
            }
        } );
        User user = sqlSever.getUser(account);
        edMail.setText(user.getGmail());
        edTen.setText(user.getTen());
        edNgaySinh.setText(user.getNgaySinh());


    }
    public void Edit(View view) {
        String mail = edMail.getText().toString();
        String ten = edTen.getText().toString();
        String NgaySinh = edNgaySinh.getText().toString();
        String NewPass = edNewPass.getText().toString();
        String Pass = edPass.getText().toString();
        if(Pass.equals("")){
            edPass.setError("Điền vào mật khẫu cũ");
        }else{
            User s = sqlSever.getUser(account);
            if (s != null) {
                if (s.getPassword().equals(Pass)) {
                    if (sqlSever.updateUser(account, NewPass,ten, mail, NgaySinh) > 0) {
                        Toast.makeText(getApplicationContext(), "Edit thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserManagerActivity.class);
                        intent.putExtra("name_user", account);
                        startActivity(intent);
                    }
                } else {
                    edPass.setError("Sai mật khẫu");
                }
            } else {
                Toast.makeText(getApplicationContext(), "Edit thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }
}