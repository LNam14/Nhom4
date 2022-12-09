package com.example.andoirdduan.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends BaseAdapter {
    private HomePage context;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;


    public HomePageAdapter(HomePage context, int layout, List<SanPham> listSP) {
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
        TextView txtTenSP,tvType,txtPrice;
        ImageView imgSP;
    }

    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.cardview_activity, null );
            viewHolder.txtTenSP = view.findViewById( R.id.tvNameShoes );
            viewHolder.tvType = view.findViewById( R.id.tvType );
            viewHolder.txtPrice = view.findViewById( R.id.tvPrice );
            viewHolder.imgSP = view.findViewById( R.id.imgShoes );
            view.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imgSP.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.chuyenTrang();
            }
        } );
        final SanPham sp = listSP.get( i );
        viewHolder.txtTenSP.setText( sp.getTenSP() );
        viewHolder.tvType.setText( sp.getThuongHieu() );
        viewHolder.txtPrice.setText(sp.getGia()+"$");
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        return view;
    }
}
