package com.example.andoirdduan.CTHD;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.GioHang.GioHangAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class LichSuHoaDon extends AppCompatActivity {
    ListView listView;
    List<DiaChi> listDC;
    List<GioHang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lich_su_hoa_don );
        listView = findViewById( R.id.lvLSHD );
        loadData1();
        DiaChiAdapter_LS adapter = new DiaChiAdapter_LS( this, R.layout.row_lshd, listDC );
        listView.setAdapter( adapter );
        Toast.makeText( this, "hih", Toast.LENGTH_SHORT ).show();
    }

    public void loadData1() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi" );
        listDC = new ArrayList<DiaChi>();
        while (cursor.moveToNext()) {
            listDC.add( new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 ) ) );
        }
    }

    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang" );
        list = new ArrayList<GioHang>();
        while (cursor.moveToNext()) {
            list.add( new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 ) ) );
        }
    }
}