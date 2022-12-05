package com.example.andoirdduan.DiaChiNhanHang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.HoaDon.HoaDonActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class DiaChiNhanHangActivity extends AppCompatActivity {
    EditText edTen_DiaChi,edSDT_DiaChi,edDiaChi1_DiaChi,edDiaChi2_DiaChi;
    Button btnHoanThanh;
    String strUsername = "";
    List<DiaChi> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang);
        edTen_DiaChi = findViewById( R.id.edTen_DiaChi );
        edSDT_DiaChi = findViewById( R.id.edSDT_DiaChi );
        edDiaChi1_DiaChi = findViewById( R.id.edDiaChi1_DiaChi );
        edDiaChi2_DiaChi = findViewById( R.id.edDiaChi2_DiaChi );
        btnHoanThanh = findViewById( R.id.btnHoanThanh_DiaChi );
        loadDataDC();
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 !=  null){
            strUsername = bundle1.getString("dulieu");
            Toast.makeText(this, "Name: " +strUsername, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Chua dc", Toast.LENGTH_SHORT).show();
        }
        btnHoanThanh.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edTen_DiaChi.getText().toString().trim();
                String sdt = edSDT_DiaChi.getText().toString().trim();
                String THX = edDiaChi1_DiaChi.getText().toString().trim();
                String soNha = edDiaChi2_DiaChi.getText().toString().trim();
                LoadingScreenActivity.db.TruyVan( "DELETE FROM DiaChi" );
                LoadingScreenActivity.db.InsertDC( hoTen, Integer.parseInt( sdt ),THX,soNha, strUsername);
                Toast.makeText( DiaChiNhanHangActivity.this, "Thêm thành công", Toast.LENGTH_SHORT ).show();
                loadData1();
            }
        } );
    }
    public void loadDataDC() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi where user ='" + strUsername + "'" );
        list = new ArrayList<DiaChi>();
        while (cursor.moveToNext()) {
            list.add( new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 )) );
        }
    }
    public void loadData1(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi where user ='" + strUsername + "'" );
        list = new ArrayList<DiaChi>();
        while (cursor.moveToNext()) {
            list.add( new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 )));
        }
    }
}