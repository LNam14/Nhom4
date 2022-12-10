package com.example.andoirdduan.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.facebook.CallbackManager;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class SanPhamActivity extends AppCompatActivity {
    EditText edMaSP, edTenSP, edGiaSP,edPhanLoai,edSoLuong,edMoTa;
    Button btnThem,btnDanhSach;
    ImageView imgSP;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
    int SELECT_PICTURE = 200;
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
        edSoLuong.setText(""+0);
        edGiaSP.setText(""+0);
        imgSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
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
                if(maSP.equals("")){
                    edMaSP.setError("Không được để trống");
                }else if(tenSP.equals("")){
                    edTenSP.setError("Không được để trống");
                }else if(phanLoai.equals("")) {
                    edPhanLoai.setError("Không được để trống");
                }else if(moTa.equals("")){
                    edMoTa.setError("Không được để trống");
                }else if(soLuong==0){
                    edMoTa.setError("Không được để trống");
                }else if(giaTien==0){
                    edMoTa.setError("Không được để trống");
                } else{
                    LoadingScreenActivity.db.Insert(maSP,tenSP,phanLoai,soLuong,giaTien,moTa,ConverttoArrayByte(imgSP),daBan);
                    Toast.makeText( SanPhamActivity.this,"Thêm thành công"+maSP,Toast.LENGTH_SHORT).show();
                }


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
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    Bitmap map = uriToBitmap(selectedImageUri);
                    imgSP.setImageBitmap(map);
                }
            }
        }
    }
    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
}