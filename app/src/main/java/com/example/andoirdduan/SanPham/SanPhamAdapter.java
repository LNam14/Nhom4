package com.example.andoirdduan.SanPham;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.R;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    SanPhamAdapter(Context context, int resource, ArrayList<SanPham> item) {
        super(context, resource, item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view ==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.row__listview, null);
        }
        SanPham sanPham=getItem(position);
        if (sanPham!=null)
        {
            ImageView imgHinhDaidien= (ImageView) view.findViewById(R.id.imgAnhDaiDien);
            TextView txtTenSP= (TextView) view.findViewById(R.id.txtTen);

            Bitmap bitmap= BitmapFactory.decodeByteArray(sanPham.hinh, 0, sanPham.hinh.length);
            imgHinhDaidien.setImageBitmap(bitmap);
            txtTenSP.setText(sanPham.getPhanLoai());
        }
        return view;
    }
}
