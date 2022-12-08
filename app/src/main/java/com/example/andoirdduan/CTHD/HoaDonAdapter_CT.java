package com.example.andoirdduan.CTHD;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter_CT extends BaseAdapter {
    SQLSever sqlSever;
    private CTHD context;
    private int layout;
    private List<GioHang> listSP;
    List<GioHang> list;
    public HoaDonAdapter_CT(CTHD context, int layout, List<GioHang> listSP) {
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
        TextView txtTenSP,tvSize,txtPrice,txtSoLuong,tvType_gioHang,txtPrice_gioHang;
        ImageView imgSP,imgSP_gioHang;
        ImageButton btnAdd_GioHang;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final GioHang sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate( R.layout.hoa_don_row_activity, null );
            viewHolder.imgSP = view.findViewById( R.id.imageView5 );
            viewHolder.txtTenSP = view.findViewById( R.id.tvTenSanPham_HoaDon );
            viewHolder.tvSize = view.findViewById( R.id.tvLoaiSanPham_HoaDon );
            viewHolder.txtPrice = view.findViewById( R.id.tvGiaSanPham_HoaDon );
            viewHolder.txtSoLuong = view.findViewById( R.id.tvSoLuong_HoaDon );
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.txtTenSP.setText( "Tên sản phẩm: "+sp.getTenSP() );
        viewHolder.tvSize.setText( "Size: "+sp.getSize() );
        viewHolder.txtSoLuong.setText( "x: "+sp.getSoLuong() );
        viewHolder.txtPrice.setText( "Giá: "+ sp.getGia()* sp.getSoLuong()+"$");
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        return view;
    }
    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
