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
        statement.bindString(2,tenSP);
        statement.bindString(3,phanLoai);
        statement.bindString(4,nhaCC);
        statement.bindString(5,moTa);
        statement.bindLong(6,gia);
        statement.bindBlob(7,hinh);
        statement.executeInsert();
    }
    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getBlob(6)));
        }
    }
}
