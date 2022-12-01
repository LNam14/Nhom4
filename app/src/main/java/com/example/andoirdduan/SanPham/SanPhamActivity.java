package com.example.andoirdduan.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SanPhamActivity extends AppCompatActivity {
    EditText edMaSP, edTenSP, edGiaSP,edPhanLoai,edSoLuong,edMoTa;
    Button btnThem,btnDanhSach;
    ImageView imgSP;
    public static ArrayList<SanPham> arraySanPham;

    int REQUEST_CODE =1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sanpham_activity );
        edTenSP = (EditText) findViewById(R.id.edTenSP);
        edGiaSP = (EditText) findViewById(R.id.edGiaTien);
        edMaSP = (EditText) findViewById(R.id.edMaSP);
        edPhanLoai = (EditText) findViewById(R.id.edPhanLoai);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
        edMoTa = (EditText) findViewById(R.id.edMota);
        btnThem = (Button) findViewById(R.id.btnThemSP);
        btnDanhSach = (Button) findViewById(R.id.btnXemDS);
        imgSP= (ImageView) findViewById(R.id.imageView);
        imgSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSP = edMaSP.getText().toString().trim();
                String tenSP = edTenSP.getText().toString().trim();
                String phanLoai = edPhanLoai.getText().toString().trim();
                int soLuong = Integer.parseInt( edSoLuong.getText().toString().trim() );
                String moTa = edMoTa.getText().toString().trim();
                int giaTien = Integer.parseInt( edGiaSP.getText().toString() );
                int daBan = 0;
                LoadingScreenActivity.db.Insert(maSP,tenSP,phanLoai,soLuong,giaTien,moTa,ConverttoArrayByte(imgSP),daBan);
                Toast.makeText( SanPhamActivity.this,"Thêm thành công"+maSP,Toast.LENGTH_SHORT).show();
            }
        });
        btnDanhSach.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DSSPActivity.class);
                startActivity( intent );
            }
        } );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSP.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}