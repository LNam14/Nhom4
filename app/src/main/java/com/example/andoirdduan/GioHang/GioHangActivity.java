package com.example.andoirdduan.GioHang;

import android.database.Cursor;
import android.os.Bundle;

import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.Home.UserActivityAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {

    ListView lvGioHang;
    ArrayList<SanPham> arraySanPham_gioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
//        lvGioHang = findViewById(R.id.lvGioHang);
//        loadData();

    }
//    public void loadData() {
//        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
//        arraySanPham_gioHang = new ArrayList<SanPham>();
//        while (cursor.moveToNext()) {
//            arraySanPham_gioHang.add(new SanPham(
//                    cursor.getString(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getInt(3),
//                    cursor.getInt(4),
//                    cursor.getString(5),
//                    cursor.getBlob(6)));
//        }
//        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, R.layout.row_gio_hang, arraySanPham_gioHang);
//        lvGioHang.setAdapter(adapter);
//    }
}