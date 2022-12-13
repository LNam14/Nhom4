package com.example.andoirdduan.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.andoirdduan.CTHD.LichSu;
import com.example.andoirdduan.DiaChiNhanHang.DiaChi;
import com.example.andoirdduan.GioHang.GioHang;
import com.example.andoirdduan.GioHang.GioHang1;
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
    public void InsertNT(String user, String date, int tien, String trangThai) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into NapTien values (?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,user);
        statement.bindString(2,date);
        statement.bindLong(3,tien);
        statement.bindString(4,trangThai);
        statement.executeInsert();
    }
    public void InsertYC(String user, String date, int tien, String phanUng) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into YeuCau values (null,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,user);
        statement.bindString(2,date);
        statement.bindLong(3,tien);
        statement.bindString(4,phanUng);
        statement.executeInsert();
    }
    public void InsertRT(String user, String date, int tien) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into RutTien values (?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,user);
        statement.bindString(2,date);
        statement.bindLong(3,tien);
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
    public void InsertHD(String hoTen, int SDT, String diaChi, String soNha, int tongTien, String user) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= "Insert into HoaDon values (null,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,hoTen);
        statement.bindLong(2,SDT);
        statement.bindString(3,diaChi);
        statement.bindString(4,soNha);
        statement.bindLong(5,tongTien);
        statement.bindString(6,user);
        statement.executeInsert();
    }
    public LichSu getHoaDon(){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from HoaDon1" );
        while (cursor.moveToNext()) {
            LichSu tt = new LichSu(
                    cursor.getInt( 0 ),
                    cursor.getString( 1 ),
                    cursor.getInt( 2 ),
                    cursor.getString( 3 ),
                    cursor.getString( 4 ),
                    cursor.getInt( 5 ),
                    cursor.getString( 6 ));

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
    public void InsertGH1(String maSP,String tenSP, String phanLoai,String size, int soLuong, int gia, String moTa, byte[] hinh,String user) {
        SQLiteDatabase db=getWritableDatabase();
        String sql= " Insert into GioHang1 values (?,?,?,?,?,?,?,?,?)";
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
    public GioHang1 getDetail1(String ma){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang1 where ID ='" + ma + "'" );
        while (cursor.moveToNext()) {
            GioHang1 gh = new GioHang1(
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
    public SanPham getSoLuong(String id){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from SanPham where ID ='" + id + "'");
        while (cursor.moveToNext()) {
            SanPham gh = new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6),
                    cursor.getInt( 7 ));
            return gh;
        }
        return null;
    }
    public GioHang getGH(String tenSP){
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe( "Select * from GioHang where tenSP = '"+tenSP+"'");
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
