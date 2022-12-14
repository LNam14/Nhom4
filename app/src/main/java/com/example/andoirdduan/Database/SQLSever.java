package com.example.andoirdduan.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.andoirdduan.User;

import java.util.ArrayList;
import java.util.List;

public class SQLSever extends SQLiteOpenHelper {
    private static final String DatabaseName = "user";
    private static final String Table_Name1 = "users";
    private static final String Account = "account";
    private static final String Email = "email";
    private static final String Password = "password";
    private static final String Ten = "ten";
    private static final String NgaySinh = "ngaysinh";
    private static final String Vi = "vi";
    private static int version = 1;

    public SQLiteDatabase db;
    private Context context;
    private ContentValues values;

    public SQLSever(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_user = " CREATE TABLE " + Table_Name1 + " ( " +
                Account + " TEXT primary key, " +
                Email + " TEXT, " +
                Password + " TEXT, " +
                Ten + " TEXT, " +
                Vi + " INTEGER, " +
                NgaySinh + " TEXT)";
        db.execSQL(Create_Table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name1);
        onCreate(db);
    }

    public void AddUser(User user) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(Account, user.getAccount());
        values.put(Email, user.getGmail());
        values.put(Password, user.getPassword());
        values.put(Ten, user.getTen());
        values.put(Vi,user.getVi());
        values.put(NgaySinh, user.getNgaySinh());

        db.insert(Table_Name1, null, values);
        db.close();
    }

    public ArrayList<User> getArrayUser(){
        ArrayList<User> list = new ArrayList<>();
        String selectUser = "select * from " + Table_Name1;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectUser, null);
        if(cursor.moveToFirst()){
            do{
                User s = new User();
                s.setAccount(cursor.getString(0));
                s.setGmail(cursor.getString(1));
                s.setPassword(cursor.getString(2));
                s.setTen(cursor.getString(3));
                s.setVi(cursor.getInt(4));
                s.setNgaySinh(cursor.getString(5));
                list.add(s);
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }
    public List<User> getAllUser() {
        List<User> dsSach = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor c = db.query(Table_Name1, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User s = new User();
            s.setAccount(c.getString(0));
            s.setGmail(c.getString(1));
            s.setPassword(c.getString(2));
            s.setTen(c.getString(3));
            s.setVi(c.getInt(4));
            s.setNgaySinh(c.getString(5));
            dsSach.add(s);
            Log.d("//=====", s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }


    public User getUser(String account){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(Table_Name1, new String[]{Account, Email, Password, Ten,Vi, NgaySinh},
                Account + "=?", new String[]{account}, null, null, null,null);
        User s = new User();
        if(cursor.moveToFirst()){
            s.setAccount(cursor.getString(0));
            s.setGmail(cursor.getString(1));
            s.setPassword(cursor.getString(2));
            s.setTen(cursor.getString(3));
            s.setVi(cursor.getInt(4));
            s.setNgaySinh(cursor.getString(5));
        }else{
            s = null;
        }
        cursor.close();
        db.close();
        return s;
    }




    public int updateNap(String account, String s ) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("vi", s);
        int result = db.update(Table_Name1, values, "account=?", new
                String[]{account});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int updateUser(String account,String newPass, String ten,String email,String NgaySinh) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPass);
        values.put("ten", ten);
        values.put("email",email);
        values.put("ngaysinh", NgaySinh);
        int result = db.update(Table_Name1, values, "account=?", new
                String[]{account});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int deleteSachByID(String maSach) {
        db = this.getWritableDatabase();
        int result = db.delete(Table_Name1, "account=?", new String[]{maSach});
        if (result == 0)
            return -1;
        return 1;
    }


}


