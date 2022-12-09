package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class UserEditManagerActivity extends AppCompatActivity {
    ListView lvUser;
    UserEditAdapter adapter = null;
    SQLSever sqlSever;
    public static List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_manager);
        sqlSever = new SQLSever(this);
        lvUser = findViewById(R.id.lvUser);
        userList = sqlSever.getAllUser();
        adapter = new UserEditAdapter(this, userList);
        lvUser.setAdapter(adapter);
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

    }



}