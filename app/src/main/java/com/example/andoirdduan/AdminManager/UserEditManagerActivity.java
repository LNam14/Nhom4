package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class UserEditManagerActivity extends AppCompatActivity {
    ListView lvUser;
    UserEditAdapter adapter = null;
    SQLSever sqlSever;
    String account = "";
    public static List<User> userList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_manager);
        sqlSever = new SQLSever(this);
        lvUser = findViewById(R.id.lvUser);
        userList = sqlSever.getAllUser();
        adapter = new UserEditAdapter(this, userList);
        lvUser.setAdapter(adapter);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name");
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                User user = (User) parent.getItemAtPosition(i);
                Intent intent = new Intent(UserEditManagerActivity.this, EditDetailUser.class);
                Bundle b = new Bundle();
                b.putString("Ten", user.getTen());
                b.putString("Account", user.getAccount());
                b.putString("Gmail", user.getGmail());
                b.putString("NgaySinh", user.getNgaySinh());
                intent.putExtras(b);
                startActivity(intent);
            }

        });
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserEditManagerActivity.this, AdminManagerActivity.class);
                intent.putExtra("name_user", account);
                startActivity( intent );
            }
        } );

    }



}