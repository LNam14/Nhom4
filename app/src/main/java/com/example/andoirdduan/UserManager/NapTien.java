package com.example.andoirdduan.UserManager;

public class NapTien {
    String user, date;
    int tien;
    String trangThai;
    public NapTien() {
    }

    public NapTien(String user, String date, int tien, String trangThai) {
        this.user = user;
        this.date = date;
        this.tien = tien;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String napTien) {
        this.trangThai = napTien;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
}
