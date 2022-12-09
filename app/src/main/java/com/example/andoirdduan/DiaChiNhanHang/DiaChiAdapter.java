package com.example.andoirdduan.DiaChiNhanHang;

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
import com.example.andoirdduan.HoaDon.HoaDonActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DiaChiAdapter extends BaseAdapter {
    SQLSever sqlSever;
    private HoaDonActivity context;
    private int layout;
    private List<DiaChi> listSP;
    List<DiaChi> list;
    public DiaChiAdapter(HoaDonActivity context, int layout, List<DiaChi> listSP) {
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
        TextView txtHoTen,txtSDT,txtTHX,txtSoNha;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final DiaChi sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_diachi, null );
            viewHolder.txtHoTen = view.findViewById( R.id.txtHoTen );
            viewHolder.txtSDT = view.findViewById( R.id.txtSDT );
            viewHolder.txtTHX = view.findViewById( R.id.txtTHX );
            viewHolder.txtSoNha = view.findViewById( R.id.txtSoNha );
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.txtHoTen.setText( sp.getHoTen() );
        viewHolder.txtSDT.setText( "(+84)"+sp.getSdt() );
        viewHolder.txtTHX.setText( sp.getTHX() );
        viewHolder.txtSoNha.setText( sp.getSoNha());
        return view;
    }

}
