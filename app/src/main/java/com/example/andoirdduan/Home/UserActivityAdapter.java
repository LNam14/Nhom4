package com.example.andoirdduan.Home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Database.SQLSeverGioHang;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.GioHang.GioHangAdapter;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserActivityAdapter extends BaseAdapter {
    SQLSeverGioHang sqlSeverGioHang;
    GioHangAdapter gioHangActivity;
    SQLSever sqlSever;
    private UserActivity context;
    private int layout;
    private List<SanPham> listSP;
    List<SanPham> list;
    List<GioHang> list1 = new ArrayList<>();
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
        viewHolder.imgSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!v.isLongClickable()) {
                    Intent intent1 = new Intent(context, ChiTietSanPham.class);
                    intent1.putExtra("chitiet", sp);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }

            }
        });
        viewHolder.btnAdd_GioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeByteArray( sp.getHinh(), 0, sp.getHinh().length );
                viewHolder.imgSP.setImageBitmap( bitmap );
                String maSP = list.get( i ).getMaSP();
                String tenSP = list.get( i ).getTenSP();
                String phanLoai = list.get( i ).getThuongHieu();
                int soLuong = 1;
                int giaTien = list.get( i ).getGia();
                String moTa = list.get( i ).getMoTa();
                GioHang gh = LoadingScreenActivity.db.getDetail( maSP );
                GioHang gh1 = LoadingScreenActivity.db.getUser( context.strUsername );
                if(gh==null||gh1==null){
                    LoadingScreenActivity.db.InsertGH(maSP,tenSP,phanLoai,soLuong,giaTien,moTa,ConverttoArrayByte( viewHolder.imgSP ),context.strUsername);
                    loadData();
                    Toast.makeText(context, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }else{
                    LoadingScreenActivity.db.TruyVan("UPDATE GioHang SET soLuong = '" + (gh.getSoLuong()+1) + "' WHERE ID = '" + maSP + "' AND user='"+context.strUsername+"'");
                    loadData();
                }
            }
        } );
        viewHolder.txtTenSP.setText( "Tên sản phẩm: "+sp.getTenSP() );
        viewHolder.tvType.setText( "Hãng: "+sp.getThuongHieu() );
        viewHolder.txtPrice.setText( "Giá: "+ sp.getGia()+"$");
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
    public void loadData(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang" );
        list1 = new ArrayList<GioHang>();
        while (cursor.moveToNext()) {
            list1.add( new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getInt( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getString( 5 ),
                    cursor.getBlob( 6 ),
                    cursor.getString( 7 )) );
        }
    }
}
