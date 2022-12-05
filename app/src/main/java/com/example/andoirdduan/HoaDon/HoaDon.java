package com.example.andoirdduan.HoaDon;

public class HoaDon {
    String tenSP,ngayTT, tenKH;
    int soLuong,donGia,tongTien;
    byte[] hinh;
    public HoaDon() {
    }

    public HoaDon(String tenSP, String ngayTT, String tenKH, int soLuong, int donGia, int tongTien, byte[] hinh) {
        this.tenSP = tenSP;
        this.ngayTT = ngayTT;
        this.tenKH = tenKH;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.hinh = hinh;
    }
    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(String ngayTT) {
        this.ngayTT = ngayTT;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
