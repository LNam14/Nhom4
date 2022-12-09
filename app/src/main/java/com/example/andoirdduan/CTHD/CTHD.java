package com.example.andoirdduan.CTHD;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHang1;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CTHD extends AppCompatActivity {
    TextView txtHoTen,txtSDT,txtTHX,txtSoNha,txtMaHD,txtTongTien;
    Button btnThem;
    ListView listView;
    Spinner spinner;
    List<GioHang1> list;
    String strUsername = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd);
        txtHoTen = findViewById(R.id.txtHoTen_CT );
        txtSDT = findViewById(R.id.txtSDT_CT);
        txtTHX = findViewById(R.id.txtTHX_CT);
        txtSoNha = findViewById(R.id.txtSoNha_CT);
        txtMaHD = findViewById(R.id.txtMaHD_CT);
        txtTongTien = findViewById(R.id.txtTongTien_CT);
        listView = findViewById( R.id.lv_rowlshd_CT );
        initData();
        loadData();
        HoaDonAdapter_CT adapter = new HoaDonAdapter_CT( CTHD.this,R.layout.hoa_don_row_activity, list);
        listView.setAdapter(adapter);
    }
    public void initData(){
        LichSu lichSu = (LichSu) getIntent().getSerializableExtra("chitiet");
        txtMaHD.setText("Mã HD: "+lichSu.getMaHD());
        txtHoTen.setText("Họ và tên: "+lichSu.getHoTen());
        txtSDT.setText("(+84): "+lichSu.getSDT());
        txtTHX.setText(lichSu.getDiaChi()+"$");
        txtSoNha.setText( lichSu.getSoNha());
        txtTongTien.setText("Tổng tiền: "+ lichSu.getTongTien()+"$");
       
    }
    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang1" );
        list = new ArrayList<GioHang1>();
        while (cursor.moveToNext()) {
            list.add( new GioHang1(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 )) );
        }

    }
}