package com.example.andoirdduan.CTHD;

public class LichSu {
    String maHD,hoTen,diaChi,soNha,tenSP;
    int SDT;
    int donGia,soLuong,tongTien;
    byte [] imgSP;
    String user;
    public LichSu() {
    }

    public LichSu(String maHD, String hoTen, int SDT, String diaChi, String soNha, String tenSP, int donGia, int soLuong, int tongTien, byte[] imgSP, String user) {
        this.maHD = maHD;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.soNha = soNha;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.imgSP = imgSP;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
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
