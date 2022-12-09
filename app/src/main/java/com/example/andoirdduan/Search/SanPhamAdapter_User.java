package com.example.andoirdduan.Search;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Home.HomePage;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter_User extends BaseAdapter {
    private DSSP_User context;
    public HomePage homePage;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;
    public SanPhamAdapter_User(DSSP_User context, int layout, List<SanPham> listSP) {
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
        TextView txtTenSP,txtGia,txtSoLuong;
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
            viewHolder.txtGia = view.findViewById( R.id.txtGiaSP);
            viewHolder.txtSoLuong = view.findViewById( R.id.txtSoLuong);
            viewHolder.imgSP = view.findViewById(R.id.imgSanPham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final SanPham sp = listSP.get(i);
        viewHolder.txtTenSP.setText(sp.getTenSP());
        viewHolder.txtGia.setText(sp.getGia()+"$");
        viewHolder.txtSoLuong.setText(sp.getSoLuong()+" sản phẩm có sẵn");
        Bitmap bitmap= BitmapFactory.decodeByteArray(sp.getHinh(), 0, sp.getHinh().length);
        viewHolder.imgSP.setImageBitmap(bitmap);

        return view;
    }
}
