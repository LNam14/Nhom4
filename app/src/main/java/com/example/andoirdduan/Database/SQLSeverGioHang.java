package com.example.andoirdduan.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andoirdduan.GioHang.GioHang;

import java.util.ArrayList;

public class SQLSeverGioHang extends SQLiteOpenHelper {
    private static final String DatabaseName = "GIOHANG";
    private static final String Table_Name2 = "GioHang1";
    private static final String ID = "id";
    private static final String TenSP = "tensp";
    private static final String TheLoai = "theloai";
    private static final String soLuong = "soluong";
    private static final String GiaTien = "giatien";
    private static final String MoTa = "mota";
    private static final String HinhAnh = "hinhanh";

    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;

    public SQLSeverGioHang(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_user = " CREATE TABLE " + Table_Name2 + " ( " +
                ID + " TEXT primary key, " +
                TenSP + " TEXT, " +
                TheLoai + " TEXT, " +
                soLuong + " INTEGER, " +
                MoTa + " TEXT, " +
                HinhAnh + " BLOB, " +
                GiaTien + " INTEGER)";
        db.execSQL(Create_Table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name2);
        onCreate(db);
    }

    public void AddGioHang(GioHang gioHang) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(ID, gioHang.getMaSP());
        values.put(TenSP, gioHang.getTenSP());
        values.put(TheLoai, gioHang.getThuongHieu());
        values.put(soLuong, gioHang.getSoLuong());
        values.put(MoTa, gioHang.getMoTa());
        values.put(HinhAnh, gioHang.getHinh());
        values.put(GiaTien, gioHang.getGia());

        db.insert(Table_Name2, null, values);
        db.close();
    }

    public ArrayList<GioHang> getArrayGioHang(){
        ArrayList<GioHang> list = new ArrayList<>();
        String selectGioHang = "select * from " + Table_Name2;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectGioHang, null);
        if(cursor.moveToFirst()){
            do{
                GioHang gioHang = new GioHang();
                gioHang.setMaSP(cursor.getString(0));
                gioHang.setTenSP(cursor.getString(1));
                gioHang.setThuongHieu(cursor.getString(2));
                gioHang.setSoLuong(cursor.getInt(3));
                gioHang.setMoTa(cursor.getString(4));
                gioHang.setHinh(cursor.getBlob(5));
                gioHang.setGia(cursor.getInt(6));
                list.add(gioHang);
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }
    public GioHang getGioHang(String id){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(Table_Name2, new String[]{ID, TenSP, TheLoai, soLuong, MoTa, HinhAnh, GiaTien},
                ID + "=?", new String[]{id}, null, null, null,null);
        GioHang gioHang = new GioHang();
        if(cursor.moveToFirst()){
            gioHang.setMaSP(cursor.getString(0));
            gioHang.setTenSP(cursor.getString(1));
            gioHang.setThuongHieu(cursor.getString(2));
            gioHang.setSoLuong(cursor.getInt(3));
            gioHang.setMoTa(cursor.getString(4));
            gioHang.setHinh(cursor.getBlob(5));
            gioHang.setGia(cursor.getInt(6));
        }else{
            gioHang = null;
        }
        cursor.close();
        db.close();
        return gioHang;
    }
}


