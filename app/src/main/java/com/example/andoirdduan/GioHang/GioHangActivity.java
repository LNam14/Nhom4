package com.example.andoirdduan.GioHang;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.R;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
    }
}