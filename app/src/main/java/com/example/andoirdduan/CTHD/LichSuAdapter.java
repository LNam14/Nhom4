package com.example.andoirdduan.CTHD;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangAdapter;
import com.example.andoirdduan.HoaDon.HoaDonActivity;
import com.example.andoirdduan.HoaDon.HoaDonAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class LichSuAdapter extends BaseAdapter {
    private LichSuHoaDon context;
    private int layout;
    private List<GioHang> listSP;
    List<GioHang> list;
    private List<DiaChi> listDC;
    List<DiaChi> list1;
    int ketqua = 0;
    int tongTien = 0;
    int doanhThu = 0;
    Spinner spinner;
    ListView listView;
    public LichSuAdapter(LichSuHoaDon context, int layout, List<DiaChi> listDC) {
        this.context = context;
        this.layout = layout;
        this.listDC = listDC;
        list1 = new ArrayList<>();
        list1.addAll( listDC );
    }


    @Override
    public int getCount() {
        return listDC.size();
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
        TextView txtMaHD, txtHoTen, txtSDT, txtDiaChi, txtSoNha,txtSize, txtTenSP,txtDonGia,txtSoLuong,txtTongTien;
        ImageView imgSP;

    }

    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        int so = 1;
        final ViewHolder viewHolder;
        final GioHang sp = listSP.get( i );
        DiaChi dc = LoadingScreenActivity.db.getDC();

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_lshd, null );
            viewHolder.txtHoTen = view.findViewById( R.id.txtHoTen_HD );
            viewHolder.txtSDT = view.findViewById( R.id.txtSDT_HD );
            viewHolder.txtDiaChi = view.findViewById( R.id.txtTHX_HD );
            viewHolder.txtSoNha = view.findViewById( R.id.txtSoNha_HD );
            viewHolder.txtSoLuong = view.findViewById( R.id.txtSoLuong );
            viewHolder.txtTongTien = view.findViewById( R.id.txtTongTien );
            listView = view.findViewById( R.id.lv_rowlshd );

            view.setTag( viewHolder );


        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        getDoanhThu();
        viewHolder.txtDiaChi.setText( "Địa chỉ: " + dc.getTHX() );
        viewHolder.txtSDT.setText( "SDT: " + dc.getSdt() + "$" );
        viewHolder.txtHoTen.setText( "Họ và tên:"+dc.getHoTen());
        viewHolder.txtSoNha.setText( "Tên đường: " + dc.getSoNha() );
        viewHolder.txtTongTien.setText("Tổng tiền:"+doanhThu+"$" );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );


        return view;
    }
    public double getDoanhThu(){

        Cursor data = LoadingScreenActivity.db.TruyVanTraVe( "SELECT SUM(soLuong*giaTien) FROM GioHang" );
        while (data.moveToNext()) {
            int tongTien = data.getInt( 0);
            doanhThu += tongTien ;
        }
        return doanhThu;
    }


}
