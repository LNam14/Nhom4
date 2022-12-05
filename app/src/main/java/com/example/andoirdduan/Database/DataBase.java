package com.example.andoirdduan.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.UserManager.ThongTin;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    ArrayList<SanPham> arraySanPham;
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public void Insert(String maSP, String tenSP, String phanLoai, int soLuong, int gia, String moTa, byte[] hinh, int daBan) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into SanPham values (?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maSP);
        statement.bindString(2,tenSP);
        statement.bindString(3,phanLoai);
        statement.bindLong(4,soLuong);
        statement.bindLong(5,gia);
        statement.bindString(6,moTa);
        statement.bindBlob(7,hinh);
        statement.bindLong(8,daBan);
        statement.executeInsert();
    }
    public void InsertDC(String hoTen, int SDT, String THX, String soNha, String user) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into DiaChi values (?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,hoTen);
        statement.bindLong(2,SDT);
        statement.bindString(3,THX);
        statement.bindString(4,soNha);
        statement.bindString(5,user);
        statement.executeInsert();
    }
    public void InsertLS(String maHD, String hoTen, int SDT, String diaChi, String soNha, String tenSP, int donGia, int soLuong, int tongTien, byte[] imgSP,String user) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into DiaChi1 values (?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maHD);
        statement.bindString(2,hoTen);
        statement.bindLong(3,SDT);
        statement.bindString(4,diaChi);
        statement.bindString(5,soNha);
        statement.bindString(6,tenSP);
        statement.bindLong(7,donGia);
        statement.bindLong(8,soLuong);
        statement.bindLong(9,tongTien);
        statement.bindBlob(10,imgSP);
        statement.bindString(11,user);
        statement.executeInsert();
    }
    public void InsertTT(String ID, String quyen, int soDu) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into ThongTin values (?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ID);
        statement.bindString(2,quyen);
        statement.bindLong(3,soDu);
        statement.executeInsert();
    }
    public ThongTin getTT(String ma){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from ThongTin where ID ='" + ma + "'" );
        while (cursor.moveToNext()) {
            ThongTin tt = new ThongTin(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getInt( 2 ));

            return tt;
        }
        return null;
    }
    public void InsertGH(String maSP,String tenSP, String phanLoai,String size, int soLuong, int gia, String moTa, byte[] hinh,String user) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= " Insert into GioHang values (?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maSP);
        statement.bindString(2,tenSP);
        statement.bindString(3,phanLoai);
        statement.bindString(4,size);
        statement.bindLong(5,soLuong);
        statement.bindLong(6,gia);
        statement.bindString(7,moTa);
        statement.bindBlob(8,hinh);
        statement.bindString(9,user);
        statement.executeInsert();
    }
    public void InsertHD(String tenSP, String date, String tenKH, int soLuong, int donGia,int tongTien) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= " Insert into GioHang values (?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,tenSP);
        statement.bindString(2,date);
        statement.bindString(3,tenKH);
        statement.bindLong(4,soLuong);
        statement.bindLong(5,donGia);
        statement.bindLong(6,tongTien);
        statement.executeInsert();
    }
    public void InsertT(int gia) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= " Insert into TongTien values (?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,gia);
        statement.executeInsert();
    }
    public GioHang getDetail(String ma){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang where ID ='" + ma + "'" );
        while (cursor.moveToNext()) {
           GioHang gh = new GioHang(
                   cursor.getString( 0 ),
                   cursor.getString( 1 ),
                   cursor.getString( 2 ),
                   cursor.getString( 3 ),
                   cursor.getInt( 4 ),
                   cursor.getInt( 5 ),
                   cursor.getString( 6 ),
                   cursor.getBlob( 7 ),
                   cursor.getString( 8 ));
            return gh;
        }
        return null;
    }
    public GioHang getUser(String user){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang where user ='" + user + "'");
        while (cursor.moveToNext()) {
            GioHang gh = new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 ));
            return gh;
        }
        return null;
    }
    public GioHang getSize(String user){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang where size ='" + user + "'");
        while (cursor.moveToNext()) {
            GioHang gh = new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 ));
            return gh;
        }
        return null;
    }
    public GioHang getGH(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang");
        while (cursor.moveToNext()) {
            GioHang gh = new GioHang(
                    cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getInt( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ),
                    cursor.getBlob( 7 ),
                    cursor.getString( 8 ));
            return gh;
        }
        return null;
    }
    public DiaChi getDC(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from DiaChi");
        while (cursor.moveToNext()) {
            DiaChi gh = new DiaChi(
                    cursor.getString( 0 ),
                    cursor.getInt( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 ));
            return gh;
        }
        return null;
    }
}
