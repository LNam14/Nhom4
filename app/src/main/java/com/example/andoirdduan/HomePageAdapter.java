package com.example.andoirdduan;

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
import com.example.andoirdduan.SanPham.SanPham;

import java.util.ArrayList;

public class HomePageAdapter extends ArrayAdapter<SanPham> {

    HomePageAdapter(Context context, int resource, ArrayList<SanPham> item) {
        super(context, resource, item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view ==null) {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.cardview_activity, null);
        }
        SanPham sanPham=getItem(position);
        if (sanPham!=null) {
            ImageView img = (ImageView) view.findViewById(R.id.imgShoes);
            TextView tenSP = (TextView) view.findViewById(R.id.tvNameShoes);
            TextView type = (TextView) view.findViewById(R.id.tvTypeShoes);
            TextView giaTien = (TextView) view.findViewById(R.id.tvPrice);

            Bitmap bitmap= BitmapFactory.decodeByteArray(sanPham.getHinh(), 0, sanPham.getHinh().length);
            img.setImageBitmap(bitmap);
            tenSP.setText(sanPham.getTenSP());
            type.setText(sanPham.getPhanLoai());
            giaTien.setText(String.valueOf(sanPham.getGia()));
        }
        return view;
    }
}
