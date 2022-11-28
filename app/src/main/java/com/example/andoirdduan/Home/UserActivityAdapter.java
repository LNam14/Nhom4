package com.example.andoirdduan.Home;

import android.database.Cursor;
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

import com.example.andoirdduan.DBUser.User;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserActivityAdapter extends BaseAdapter {
    SQLSever sqlSever;
    private UserActivity context;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;
    List<SanPham> list1 = new ArrayList<>();
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
        TextView txtTenSP,tvType,txtPrice,txtTenSP_gioHang,tvType_gioHang,txtPrice_gioHang;
        ImageView imgSP,imgSP_gioHang;
        ImageButton btnAdd_GioHang;
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
            viewHolder.btnAdd_GioHang = view.findViewById( R.id.btnAdd );

            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.btnAdd_GioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GioHang gh = new GioHang();
                String ma = gh.getMaSP();
                Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
                viewHolder.imgSP.setImageBitmap( bitmap );
                String maSP = list.get( i ).getMaSP();
                String tenSP = list.get( i ).getTenSP();
                String phanLoai = list.get( i ).getPhanLoai();
                int soLuong = list.get( i ).getSoLuong();
                int giaTien = list.get( i ).getGia();
                String moTa = list.get( i ).getMoTa();
                if ( list1.size()<0) {
                    LoadingScreenActivity.db.InsertGH(maSP,tenSP,phanLoai,soLuong,giaTien,moTa,ConverttoArrayByte(viewHolder.imgSP));
                    Toast.makeText( context, "Thêm thành công", Toast.LENGTH_SHORT ).show();
                }else{
                    LoadingScreenActivity.db.TruyVan("UPDATE GioHang SET soLuong = '" + (list1.get( i ).getSoLuong()+1) + "' WHERE ID = '" + maSP + "'");
                    Toast.makeText( context, "Thêm thành công jiji"+list1.get( i ).getSoLuong(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        viewHolder.txtTenSP.setText( sp.getTenSP() );
        viewHolder.tvType.setText( sp.getPhanLoai() );
        viewHolder.txtPrice.setText( String.valueOf( sp.getGia() ) );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        return view;
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
