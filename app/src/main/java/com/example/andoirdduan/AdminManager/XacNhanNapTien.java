package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class XacNhanNapTien extends AppCompatActivity {
    ListView lvXNNT;
    String strUsername = "";
    List<YeuCau> list;
    int i;
    SQLSever sqlSever = new SQLSever(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_nap);
        lvXNNT = findViewById( R.id.lvXacNhapNap );
        loadData();
    }
    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from YeuCau " );
        list = new ArrayList<YeuCau>();
        while (cursor.moveToNext()) {
            list.add( new YeuCau(
                    cursor.getInt( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getInt( 3 ),
                    cursor.getString( 4 )));
        }
        YeuCauAdapter adapter = new YeuCauAdapter( XacNhanNapTien.this,R.layout.hoa_don_row_activity, list);
        lvXNNT.setAdapter(adapter);
    }
}