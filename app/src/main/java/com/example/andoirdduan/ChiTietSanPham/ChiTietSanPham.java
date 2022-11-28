package com.example.andoirdduan.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnThem;
    ImageView imgSanPham;
    Spinner spinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        tensp = findViewById(R.id.edTenSanPhamChiTiet);
        giasp = findViewById(R.id.edGiaSanPham);
        mota = findViewById(R.id.txtMoTaChiTiet);
        btnThem = findViewById(R.id.btnThemVaoGioHang);
        imgSanPham = findViewById(R.id.img_chitiet);
        spinner = findViewById(R.id.spinner);
        initData();
    }

    public void initData(){
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanPham.getTenSP());
        mota.setText(sanPham.getMoTa());
        giasp.setText("Giá: "+sanPham.getGia()+"Đ");
        Bitmap bitmap = BitmapFactory.decodeByteArray( sanPham.getHinh(), 0, sanPham.getHinh().length );
        imgSanPham.setImageBitmap( bitmap );
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapterspin);
    }
}