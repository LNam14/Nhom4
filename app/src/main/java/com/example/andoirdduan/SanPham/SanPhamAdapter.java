package com.example.andoirdduan.SanPham;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private DSSPActivity context;
    public HomePage homePage;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;
    public SanPhamAdapter(DSSPActivity context, int layout, List<SanPham> listSP) {
        this.context = context;
        this.layout = layout;
        this.listSP = listSP;
        list = new ArrayList<>();
        list.addAll(listSP);
    }

    @Override
    public int getCount() {
        return listSP.size();
    }

    @Override
    public SanPham getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView txtTenSP;
        ImageView imgSP;
    }

    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder ;
        if (view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.row__listview, null);
            viewHolder.txtTenSP = view.findViewById( R.id.txtTenSP);
            viewHolder.imgSP = view.findViewById(R.id.imgSanPham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final SanPham sp = listSP.get(i);
        viewHolder.txtTenSP.setText(sp.getTenSP());
        Bitmap bitmap= BitmapFactory.decodeByteArray(sp.getHinh(), 0, sp.getHinh().length);
        viewHolder.imgSP.setImageBitmap(bitmap);

        return view;
    }
    public void Search(String text) {
        list.clear();
        if (text.length() == 0) {
            listSP.addAll(list);
        } else {
            for (SanPham st : list) {
                if (st.getTenSP().toLowerCase().contains(text.toLowerCase())) {
                    listSP.add(st);
                }
                //ss
                notifyDataSetChanged();
            }
        }
    }

}
