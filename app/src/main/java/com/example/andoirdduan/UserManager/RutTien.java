package com.example.andoirdduan.UserManager;

public class RutTien {
    String user, date;
    int tien;

    public RutTien() {
    }

    public RutTien(String user, String date, int tien) {
        this.user = user;
        this.date = date;
        this.tien = tien;
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
