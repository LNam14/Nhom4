package com.example.andoirdduan.SanPham;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class SanPham implements Serializable{
    String maSP,tenSP, thuongHieu;
    int soLuong,Gia;
    String moTa;
    byte[] hinh;
    int daBan;
    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String thuongHieu, int soLuong, int gia, String moTa, byte[] hinh,int daBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.thuongHieu = thuongHieu;
        this.Gia = gia;
        this.moTa = moTa;
        this.hinh = hinh;
        this.daBan = daBan;
    }

    public int getDaBan() {
        return daBan;
    }

    public void setDaBan(int daBan) {
        this.daBan = daBan;
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

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
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
