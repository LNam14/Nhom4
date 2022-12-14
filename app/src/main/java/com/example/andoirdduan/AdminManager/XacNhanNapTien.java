package com.example.andoirdduan.AdminManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    String account = "";
    SQLSever sqlSever = new SQLSever(this);
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle_giohang );
        ImageView btnBack = findViewById( R.id.btnBack );
        super.onCreate(savedInstanceState);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name");
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XacNhanNapTien.this, AdminManagerActivity.class);
                intent.putExtra("name_user", account);
                startActivity( intent );
            }
        } );
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