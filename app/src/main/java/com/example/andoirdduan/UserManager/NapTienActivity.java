package com.example.andoirdduan.UserManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.R;
import com.example.andoirdduan.User;

public class NapTienActivity extends AppCompatActivity {
    EditText Tien;
    String account = "";
    int tien = 0;

    int tienNap = 0;
    SQLSever sqlSever;
    Button btnNapTien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien);
        Tien = findViewById(R.id.edTienNap);
        btnNapTien = findViewById(R.id.btnNapTien);
        sqlSever = new SQLSever(this);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");
        tien = bundle1.getInt("Tien");
        Toast.makeText(this, "Tien: " +tien, Toast.LENGTH_SHORT).show();






    }
    public void Nap(View view) {
        int tien2 = Integer.parseInt(Tien.getText().toString());
        tienNap = tien + tien2;
        System.out.println("Tien2: " +tien2);
        System.out.println("Tien: " +tien);
            if (sqlSever.updateNap(account, String.valueOf(tienNap)) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UserManagerActivity.class);
                startActivity(intent);
        }
    }




}