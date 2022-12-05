package com.example.andoirdduan.DiaChiNhanHang;

public class DiaChi {
    String hoTen;
    int sdt;
    String THX,soNha,user;

    public DiaChi() {
    }

    public DiaChi(String hoTen, int sdt, String THX, String soNha, String user) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.THX = THX;
        this.soNha = soNha;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getTHX() {
        return THX;
    }

    public void setTHX(String THX) {
        this.THX = THX;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }
}
