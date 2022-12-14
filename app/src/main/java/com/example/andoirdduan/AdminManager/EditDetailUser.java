package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;
import com.example.andoirdduan.UserManager.EditUserActivity;
import com.example.andoirdduan.UserManager.UserManagerActivity;

public class EditDetailUser extends AppCompatActivity {
    EditText edMailEdit, edTenEdit,edNgaySinhEdit,edNewPassEdit;
    String mail,ten,NgaySinh,Account,NewPass;
    SQLSever sqlSever;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail_user);
        edMailEdit = findViewById(R.id.edMailEdit);
        edTenEdit = findViewById(R.id.edTenEdit);
        edNgaySinhEdit = findViewById(R.id.edNgaySinhEdit);
        edNewPassEdit = findViewById(R.id.edNewPassEdit);
        sqlSever = new SQLSever(this);

        Intent in = getIntent();
        Bundle k = in.getExtras();

        mail = k.getString("Gmail");
        ten = k.getString("Ten");
        NgaySinh = k.getString("NgaySinh");
        Account = k.getString("Account");


        edMailEdit.setText(mail);
        edTenEdit.setText(ten);
        edNgaySinhEdit.setText(NgaySinh);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDetailUser.this, AdminManagerActivity.class);
                intent.putExtra("name_user", ten);
                startActivity( intent );
            }
        } );

    }public void Edit1(View view) {
            String Mail = edMailEdit.getText().toString();
            String Ten = edTenEdit.getText().toString();
            String Pass = edNewPassEdit.getText().toString();
            String ngaySinh = edNgaySinhEdit.getText().toString();
            if(edNewPassEdit.equals("")){
                edNewPassEdit.setError("Không được đễ trống");
            } else if (sqlSever.updateUser(Account, Pass,Ten,Mail,ngaySinh) > 0) {
                Toast.makeText(getApplicationContext(), "Edit thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UserEditManagerActivity.class);
                startActivity(intent);
            }


    }
}