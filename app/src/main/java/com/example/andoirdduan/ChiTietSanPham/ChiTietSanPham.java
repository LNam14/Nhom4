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
    TextView tensp, giasp, mota, soluong,thuonghieu;
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
        mota = findViewById(R.id.moTaSP);
        btnThem = findViewById(R.id.btnThemVaoGioHang);
        imgSanPham = findViewById(R.id.img_chitiet);
        soluong = findViewById(R.id.txtSoLuong);
        thuonghieu = findViewById( R.id.txtThuongHieu );
        initData();


    }

    public void initData(){
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("chitiet");
        tensp.setText("Tên sản phẩm: "+sanPham.getTenSP());
        mota.setText("Mô tả: "+sanPham.getMoTa());
        thuonghieu.setText("Thương hiệu: "+sanPham.getThuongHieu());
        giasp.setText("Giá: "+sanPham.getGia()+"$");
        Bitmap bitmap = BitmapFactory.decodeByteArray( sanPham.getHinh(), 0, sanPham.getHinh().length );
        imgSanPham.setImageBitmap( bitmap );
       soluong.setText( sanPham.getSoLuong()+" sản phẩm có sẵn");
    }
}