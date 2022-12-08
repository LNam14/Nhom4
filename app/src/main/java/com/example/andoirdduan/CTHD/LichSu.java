package com.example.andoirdduan.CTHD;

import android.service.autofill.Sanitizer;

import java.io.Serializable;

public class LichSu implements Serializable {
    int maHD;
    String hoTen,diaChi,soNha,tenSP;
    int SDT;
    int donGia,soLuong,tongTien;
    byte [] imgSP;
    String user;
    public LichSu() {
    }

    public LichSu(int maHD, String hoTen, int SDT, String diaChi, String soNha, int tongTien) {
        this.maHD = maHD;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.soNha = soNha;
        this.tongTien = tongTien;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public byte[] getImgSP() {
        return imgSP;
    }

    public void setImgSP(byte[] imgSP) {
        this.imgSP = imgSP;
    }
}
