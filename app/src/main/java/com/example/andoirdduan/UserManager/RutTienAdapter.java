package com.example.andoirdduan.UserManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.util.ArrayList;
import java.util.List;

public class RutTienAdapter extends BaseAdapter {
    SQLSever sqlSever;
    private RutTienActivity context;
    private int layout;
    private List<RutTien> listSP;
    List<RutTien> list;
    public RutTienAdapter(RutTienActivity context, int layout, List<RutTien> listSP) {
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
        TextView txtDate,txtTien;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final RutTien sp = listSP.get( i );
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( context );
            view = inflater.inflate( R.layout.row_naptien, null );
            viewHolder.txtDate = view.findViewById( R.id.txtDate_NT );
            viewHolder.txtTien = view.findViewById( R.id.txtSoTien_NT );
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.txtDate.setText( sp.getDate() );
        viewHolder.txtTien.setText( "-"+sp.getTien() +"$");
        return view;
    }

}
