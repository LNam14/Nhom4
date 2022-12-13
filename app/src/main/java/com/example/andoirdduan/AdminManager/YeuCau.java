package com.example.andoirdduan.AdminManager;

public class YeuCau {
    int ID;
    String user, date;
    int tien;
    String phanUng;

    public YeuCau() {
    }

    public YeuCau(int ID, String user, String date, int tien, String phanUng) {
        this.ID = ID;
        this.user = user;
        this.date = date;
        this.tien = tien;
        this.phanUng = phanUng;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhanUng() {
        return phanUng;
    }

    public void setPhanUng(String phanUng) {
        this.phanUng = phanUng;
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
