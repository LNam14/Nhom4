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
    List<LichSu> listDC;
    List<GioHang> list;
    String strUsername = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lich_su_hoa_don );
        listView = findViewById( R.id.lvLSHD );


        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 !=  null){
            strUsername = bundle1.getString("dulieu");
            Toast.makeText(this, "Name: " +strUsername, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Chua dc", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText( this, "name "+strUsername, Toast.LENGTH_SHORT ).show();
        loadData1();
        DiaChiAdapter_LS adapter = new DiaChiAdapter_LS( this, R.layout.row_lshd, listDC );
        listView.setAdapter( adapter );
    }

    public void loadData1() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from HoaDon where user = '"+ strUsername+ "'" );
        listDC = new ArrayList<LichSu>();
        while (cursor.moveToNext()) {
            listDC.add( new LichSu(
                    cursor.getInt( 0 ),
                    cursor.getString( 1 ),
                    cursor.getInt( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 )));
        }
    }

    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang1 where user = '"+ strUsername+ "'" );
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