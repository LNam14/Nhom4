package com.example.andoirdduan;

public class
User {
    private String Account, Gmail, Password, Ten, NgaySinh;
    private int Vi;

    public User() {
    }

    public User(String account, String gmail, String password, String ten, String ngaySinh, int vi) {
        Account = account;
        Gmail = gmail;
        Password = password;
        Ten = ten;
        NgaySinh = ngaySinh;
        Vi= vi;
    }

    public int getVi() {
        return Vi;
    }

    public void setVi(int vi) {
        Vi = vi;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }
}
