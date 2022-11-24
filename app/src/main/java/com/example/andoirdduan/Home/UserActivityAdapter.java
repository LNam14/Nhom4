package com.example.andoirdduan.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserActivityAdapter extends BaseAdapter {
    private UserActivity context;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;

    public UserActivityAdapter(UserActivity context, int layout, List<SanPham> listSP) {
        this.context = context;
        this.layout = layout;
        this.listSP = listSP;
        list = new ArrayList<>();
        list.addAll( listSP );
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
        TextView txtTenSP,tvType,txtPrice,txtTenSP_gioHang,tvType_gioHang,txtPrice_gioHang,imgSP_gioHang;
        ImageView imgSP;
        Button btnAdd_GioHang;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final SanPham sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.cardview_activity, null );
            viewHolder.txtTenSP = view.findViewById( R.id.tvNameShoes );
            viewHolder.tvType = view.findViewById( R.id.tvType );
            viewHolder.txtPrice = view.findViewById( R.id.tvPrice );
            viewHolder.imgSP = view.findViewById( R.id.imgShoes );
            viewHolder.btnAdd_GioHang = view.findViewById(R.id.btnAdd);
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.imgSP.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.chuyenTrang();
            }
        } );

//        final ViewHolder viewHolder1;
//        if (view == null) {
//            viewHolder1 = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from( context );
//            view = inflater.inflate( R.layout.row_gio_hang, null );
//            viewHolder1.txtTenSP_gioHang = view.findViewById( R.id.edTen );
//            viewHolder1.tvType_gioHang = view.findViewById( R.id.edLoai );
//            viewHolder1.txtPrice_gioHang = view.findViewById( R.id.edGia );
//            viewHolder1.imgSP_gioHang = view.findViewById( R.id.imgSanPham_gioHang );
//
//            view.setTag( viewHolder );
//        } else {
//            viewHolder1 =(ViewHolder) view.getTag();
//        }

        viewHolder.btnAdd_GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               viewHolder1.txtTenSP_gioHang.setText(sp.getTenSP());
//               viewHolder1.txtPrice_gioHang.setText(sp.getGia());
//               viewHolder1.tvType_gioHang.setText(sp.getPhanLoai());
//               viewHolder1.imgSP_gioHang.setText(sp.getHinh());

            }
        });

        viewHolder.txtTenSP.setText( sp.getTenSP() );
        viewHolder.tvType.setText( sp.getPhanLoai() );
        viewHolder.txtPrice.setText( String.valueOf( sp.getGia() ) );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        return view;
    }
}
