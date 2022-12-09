package com.example.andoirdduan.GioHang;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.CTHD.LichSuHoaDon;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
    private GioHangActivity context;
    private int layout;
    private List<GioHang> listSP;
    List<GioHang> list;
    int ketqua = 0;
    int tongTien = 0;
    Spinner spinner;
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
        TextView txtTenSP, tvType, txtPrice, txtSo, tvType_gioHang, txtPrice_gioHang;
        ImageView imgSP, imgSP_gioHang;
        ImageButton tang, giam, imgXoa;
        CheckBox ckChonMua;
        Button btnMua;

    }

    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        int so = 1;
        final ViewHolder viewHolder;
        final GioHang sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_giohang, null );
            viewHolder.txtTenSP = view.findViewById( R.id.edTenSP_GH );
            viewHolder.tvType = view.findViewById( R.id.edGiaSP_GH );
            viewHolder.txtSo = view.findViewById( R.id.soluong );
            viewHolder.txtPrice = view.findViewById( R.id.edGiaSP_GH );
            viewHolder.imgSP = view.findViewById( R.id.img_GH );
            viewHolder.tang = view.findViewById( R.id.tang );
            viewHolder.giam = view.findViewById( R.id.giam );
            spinner = view.findViewById( R.id.spinner_size );
            viewHolder.imgXoa = view.findViewById( R.id.imgXoa );

            viewHolder.txtSo.setText( String.valueOf( so ) );
            view.setTag( viewHolder );

            ketqua = list.get( i ).getSoLuong();
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ketqua += 1;
                String maSP = list.get( i ).getTenSP();
                LoadingScreenActivity.db.TruyVan( "Update GioHang set soLuong = '" + ketqua + "' WHERE tenSP = '" + maSP + "'" );
                context.getDoanhThu();
                context.loadData();
                viewHolder.txtSo.setText( "" + ketqua );
            }
        } );
        viewHolder.giam.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ketqua -= 1;
                String maSP = list.get( i ).getTenSP();
                LoadingScreenActivity.db.TruyVan( "Update GioHang set soLuong = '" + ketqua + "' WHERE tenSP = '" + maSP + "'" );
                context.getDoanhThu();
                context.loadData();
                viewHolder.txtSo.setText( "" + ketqua );
            }
        } );
        viewHolder.imgXoa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang sp = list.get( i );
                final String maSP = sp.getMaSP();
                LoadingScreenActivity.db.TruyVan( "DELETE FROM GioHang WHERE ID='" + maSP + "'" );
                Toast.makeText( context, "Đã Xóa ", Toast.LENGTH_SHORT ).show();
                context.loadData();
                context.getDoanhThu();
            }
        } );
        List<String> listSize = new ArrayList<>();
        listSize.add(0,"Size 37");
        listSize.add("Size 38");
        listSize.add("Size 39");
        listSize.add("Size 40");
        listSize.add("Size 41");
        listSize.add("Size 42");
        listSize.add("Size 43");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listSize);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        viewHolder.txtTenSP.setText( sp.getTenSP() );
        viewHolder.tvType.setText( sp.getGia() + "$" );
        viewHolder.txtSo.setText( String.valueOf( sp.getSoLuong() ) );
        Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
        viewHolder.imgSP.setImageBitmap( bitmap );
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String maSP = listSP.get( i ).getMaSP();
                LoadingScreenActivity.db.TruyVan( "UPDATE GioHang SET size = '" + spinner.getSelectedItem().toString() + "' WHERE ID = '" + maSP + "'" );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        return view;
    }
}
