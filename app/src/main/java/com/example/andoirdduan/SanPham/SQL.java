package com.example.andoirdduan.SanPham;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.ImageView;

import com.example.andoirdduan.Login.LoadingScreenActivity;

import java.util.ArrayList;

public class SQL extends SQLiteOpenHelper {
    ArrayList<SanPham> arraySanPham;
    public SQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void TruyVan(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void Insert(String maSP,String tenSP, String phanLoai, String nhaCC, String moTa, Integer gia, byte[] hinh) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into SanPham values (?,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maSP);
        statement.bindString(2,phanLoai);
        statement.bindString(3,nhaCC);
        statement.bindString(4,tenSP);
        statement.bindLong(5,gia);
        statement.bindString(6,moTa);
        statement.bindBlob(7,hinh);
        statement.executeInsert();
    }

}
