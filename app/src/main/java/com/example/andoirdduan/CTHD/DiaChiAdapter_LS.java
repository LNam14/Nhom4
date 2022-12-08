package com.example.andoirdduan.CTHD;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.HoaDon.HoaDonActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiaChiAdapter_LS extends BaseAdapter {
    SQLSever sqlSever;
    public LichSuHoaDon context;
    private int layout;
    List<GioHang> listGH;
    private List<LichSu> listSP;
    List<LichSu> list;
    public DiaChiAdapter_LS(LichSuHoaDon context, int layout, List<LichSu> listSP) {
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
        TextView txtHoTen,txtSDT,txtTHX,txtSoNha,txtMaHD,txtTongTien;
        ListView imageView;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final LichSu ls = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_lshd, null );
            viewHolder.txtHoTen = view.findViewById( R.id.txtHoTen_HD );
            viewHolder.txtSDT = view.findViewById( R.id.txtSDT_HD );
            viewHolder.txtTHX = view.findViewById( R.id.txtTHX_HD );
            viewHolder.txtSoNha = view.findViewById( R.id.txtSoNha_HD );
            viewHolder.imageView = view.findViewById( R.id.lv_rowlshd );
            viewHolder.txtMaHD = view.findViewById( R.id.txtMaHD );
            viewHolder.txtTongTien = view.findViewById( R.id.txtTongTien );
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.imageView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(context, CTHD.class);
                intent1.putExtra("chitiet", ls );
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        } );

        viewHolder.txtMaHD.setText("Mã hóa đơn: "+ls.getMaHD());
        viewHolder.txtHoTen.setText( "Tên: "+ls.getHoTen() );
        viewHolder.txtSDT.setText( "SDT: +84"+ls.getSDT() );
        viewHolder.txtTHX.setText( "Tỉnh: "+ls.getDiaChi() );
        viewHolder.txtSoNha.setText( "Số nhà: "+ ls.getSoNha());
        viewHolder.txtTongTien.setText( "Tổng tiền :"+ls.getTongTien() );
        loadData();
        HoaDonAdapter_LS adapter = new HoaDonAdapter_LS( DiaChiAdapter_LS.this,R.layout.hoa_don_row_activity, listGH);
        viewHolder.imageView.setAdapter(adapter);
        return view;
    }

    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang" );
        listGH = new ArrayList<GioHang>();
        while (cursor.moveToNext()) {
            listGH.add( new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 )) );
        }

    }
}
