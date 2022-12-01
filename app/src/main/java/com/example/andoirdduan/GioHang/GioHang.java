package com.example.andoirdduan.GioHang;

public class GioHang {
    String maSP,tenSP, thuongHieu;
    int soLuong,Gia;
    String moTa;
    byte[] hinh;
    String user;
    public GioHang() {
    }

    public GioHang(String maSP,String tenSP, String phanLoai, int soLuong, int gia, String moTa, byte[] hinh, String user) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.thuongHieu = phanLoai;
        this.Gia = gia;
        this.moTa = moTa;
        this.hinh = hinh;
        this.user =user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setPhanLoai(String phanLoai) {
        this.thuongHieu = phanLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
