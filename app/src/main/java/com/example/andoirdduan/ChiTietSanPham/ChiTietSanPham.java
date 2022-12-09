package com.example.andoirdduan.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tensp, giasp, mota, soluong,thuonghieu,txtDaBan;
    Button btnThem;
    ImageView imgSanPham;
    Spinner spinner;
    private List<SanPham> listSP;
    String strUsername = "";
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
        txtDaBan = findViewById( R.id.txtDaBan );
        initData();
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 !=  null){
            strUsername = bundle1.getString("dulieu");
            Toast.makeText(this, "Name: " +strUsername, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Chua dc", Toast.LENGTH_SHORT).show();
        }
        btnThem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = (SanPham) getIntent().getSerializableExtra("chitiet");
                Bitmap bitmap = BitmapFactory.decodeByteArray( sanPham.getHinh(), 0, sanPham.getHinh().length );
                imgSanPham.setImageBitmap( bitmap );
                String maSP = sanPham.getMaSP();
                String tenSP = sanPham.getTenSP();
                String phanLoai = sanPham.getThuongHieu();
                int soLuong = 1;
                String size = "Size 37";
                int giaTien = sanPham.getGia();
                int tongTien = sanPham.getGia()*sanPham.getSoLuong();
                String moTa = sanPham.getMoTa();
                GioHang gh = LoadingScreenActivity.db.getDetail( maSP );
                GioHang gh1 = LoadingScreenActivity.db.getUser( strUsername );
                if(gh==null||gh1==null){
                    LoadingScreenActivity.db.InsertGH(maSP,tenSP,phanLoai,size,soLuong,giaTien,moTa,ConverttoArrayByte( imgSanPham ),strUsername);
                    System.out.println("Tien"+tongTien);
                    Toast.makeText(getBaseContext(), "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }else{
                    LoadingScreenActivity.db.TruyVan("UPDATE GioHang SET soLuong = '" + (gh.getSoLuong()+1) + "' WHERE ID = '" + maSP + "' AND user='"+strUsername+"'");
                }
            }
        } );
    }
    public void initData(){
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanPham.getTenSP());
        mota.setText(sanPham.getMoTa());
        thuonghieu.setText(sanPham.getThuongHieu());
        giasp.setText(sanPham.getGia()+"$");
        txtDaBan.setText( "Đã bán "+sanPham.getDaBan() );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sanPham.getHinh(), 0, sanPham.getHinh().length );
        imgSanPham.setImageBitmap( bitmap );
       soluong.setText( sanPham.getSoLuong()+" sản phẩm có sẵn");
    }
    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}