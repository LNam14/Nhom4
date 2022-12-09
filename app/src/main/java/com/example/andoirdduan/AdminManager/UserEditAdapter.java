package com.example.andoirdduan.AdminManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class UserEditAdapter extends BaseAdapter {
    SQLSever sqlSever;
    private Activity context;
     List<User> listSP;
    public LayoutInflater inflater;

    public UserEditAdapter(Activity context, List<User> listSP) {
        super();
        this.context = context;
        this.listSP = listSP;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sqlSever = new SQLSever(context);
    }


    @Override
    public int getCount() {
        return listSP.size();
    }

    @Override
    public Object getItem(int i) {
        return listSP.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView AccountUser,NameUser,EmailUser;
        ImageView imgSP,imgXoaUser;
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final User sp = listSP.get(i);

        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.user_row, null);
            viewHolder.AccountUser = view.findViewById( R.id.AccountUser );
            viewHolder.NameUser = view.findViewById( R.id.NameUser );
            viewHolder.EmailUser = view.findViewById( R.id.EmailUser );
            viewHolder.imgSP = view.findViewById( R.id.imgUser );
            viewHolder.imgXoaUser = view.findViewById(R.id.imgXoaUser);

            viewHolder.imgXoaUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqlSever.deleteSachByID(listSP.get(i).getAccount());
                    listSP.remove(i);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            viewHolder.imgSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = listSP.get(i);
                    Intent intent = new Intent(context, EditDetailUser.class);
                    Bundle b = new Bundle();
                    b.putString("Ten", user.getTen());
                    b.putString("Account", user.getAccount());
                    b.putString("Gmail", user.getGmail());
                    b.putString("NgaySinh", user.getNgaySinh());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            view.setTag( viewHolder );
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }


        viewHolder.AccountUser.setText( "Account: "+sp.getAccount() );
        viewHolder.NameUser.setText( "Tên: "+sp.getTen() );
        viewHolder.EmailUser.setText( "Gmail: "+ sp.getGmail());
        return view;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }



}
