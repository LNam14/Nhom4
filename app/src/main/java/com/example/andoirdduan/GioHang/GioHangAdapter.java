package com.example.andoirdduan.GioHang;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {

    private GioHangActivity context;
    private int layout;
    private List<GioHang> listSP;
    List<GioHang> list;
    public GioHangAdapter(GioHangActivity context, int layout, List<GioHang> listSP) {
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
    public GioHang getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView txtTenSP,tvType,txtPrice,txtSo,tvType_gioHang,txtPrice_gioHang;
        ImageView imgSP,imgSP_gioHang;
        ImageButton btnMua,tang,giam;

    }
    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        int so = 1;
        final ViewHolder viewHolder;
        final GioHang sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_gio_hang, null );
            viewHolder.txtTenSP = view.findViewById( R.id.edTen );
            viewHolder.tvType = view.findViewById( R.id.edLoai );
            viewHolder.txtSo = view.findViewById( R.id.soluong );
            viewHolder.txtPrice = view.findViewById( R.id.edGia );
            viewHolder.imgSP = view.findViewById( R.id.imgSanPham_gioHang );
            viewHolder.btnMua = view.findViewById( R.id.imageButton );
            viewHolder.tang = view.findViewById( R.id.tang );
            viewHolder.giam = view.findViewById( R.id.giam );

            viewHolder.txtSo.setText(String.valueOf( so ));
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.tang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.txtSo.setText(String.valueOf( so+1 ));
            }
        } );
        viewHolder.giam.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.txtSo.setText(String.valueOf( so-1 ));
            }
        } );
        viewHolder.btnMua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSP = listSP.get( i ).getTenSP();
                LoadingScreenActivity.db.TruyVan("DELETE FROM GioHang WHERE tenSP='" + tenSP + "'");
                Toast.makeText( context, "Mua thành công", Toast.LENGTH_SHORT ).show();
                context.loadData();
            }
        } );
        viewHolder.txtTenSP.setText( sp.getTenSP() );
        viewHolder.tvType.setText( sp.getPhanLoai() );
        viewHolder.txtPrice.setText(String.valueOf( sp.getSoLuong() ) );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        return view;
    }
}
