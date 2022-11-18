package com.example.andoirdduan.SanPham;

public class SanPham {
    String maSP,tenSP, phanLoai, nhaCC,moTa;
    int Gia;
    byte[] hinh;

    public SanPham() {
    }

    public SanPham(String maSP, String phanLoai, String nhaCC, String tenSP, int gia, String moTa, byte[] hinh) {
        this.tenSP = tenSP;
        this.phanLoai = phanLoai;
        this.nhaCC = nhaCC;
        this.moTa = moTa;
        Gia = gia;
        this.hinh = hinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getNhaCC() {
        return nhaCC;
    }

    public void setNhaCC(String nhaCC) {
        this.nhaCC = nhaCC;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
