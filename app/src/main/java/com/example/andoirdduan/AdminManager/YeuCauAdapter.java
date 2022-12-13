package com.example.andoirdduan.AdminManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.UserManager.NapTien;
import com.example.andoirdduan.User;
import java.util.ArrayList;
import java.util.List;

public class YeuCauAdapter extends BaseAdapter {
    private XacNhanNapTien context;
    private int layout;
    private List<YeuCau> listSP;
    List<YeuCau> list;
    List<NapTien> listNT = new ArrayList<>();
    int doanhThu = 0;
    public YeuCauAdapter(XacNhanNapTien context, int layout, List<YeuCau> listSP) {
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
        Button btnXN, btnTC;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final YeuCau sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_xac_nhan_nap, null );
            viewHolder.txtHoTen = view.findViewById( R.id.tvNameUser );
            viewHolder.txtSDT = view.findViewById( R.id.tvMoney );
            viewHolder.txtTHX = view.findViewById( R.id.tvTime_Date );
            viewHolder.btnXN = view.findViewById( R.id.btnXacNhan_YC );
            viewHolder.btnTC = view.findViewById( R.id.btnTuChoi );
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.btnXN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = sp.getUser();
                String time = sp.getDate();
                int tienNap = sp.getTien();
                User s = context.sqlSever.getUser12(account);
                int tongTien = s.getVi();
                int tongTien1 = tienNap + tongTien;
                String trangThai = "Đã xác nhận";
                int id = sp.getID();
                Toast.makeText( context, "acount"+account + "tien"+tienNap+"tt"+s.getVi(), Toast.LENGTH_SHORT ).show();
                if (context.sqlSever.updateNap(account, String.valueOf(tongTien1)) > 0) {
                    Toast.makeText(context, "Đã xác nhận yêu cầu", Toast.LENGTH_SHORT).show();
                    LoadingScreenActivity.db.InsertNT(account, time, tienNap,trangThai);
                    LoadingScreenActivity.db.TruyVan( "DELETE FROM YeuCau where ID = '"+ id + "'");
                    context.loadData();
                }
            }
        } );
        viewHolder.btnTC.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = sp.getUser();
                String time = sp.getDate();
                int tienNap = sp.getTien();
                int id = sp.getID();
                String trangThai = "Bị từ chối";
                LoadingScreenActivity.db.InsertNT(account, time, tienNap,trangThai);
                Toast.makeText(context, "Đã từ chối yêu cầu", Toast.LENGTH_SHORT).show();
                LoadingScreenActivity.db.TruyVan( "DELETE FROM YeuCau where ID = '"+ id + "'");
                context.loadData();
            }
        } );
        viewHolder.txtHoTen.setText( sp.getUser() );
        viewHolder.txtSDT.setText(sp.getTien()+"$");
        viewHolder.txtTHX.setText( sp.getDate() );
        return view;
    }
}
