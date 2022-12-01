package com.example.andoirdduan.DBUser;

public class
User {
    private String Account, Gmail, Password, Ten, NgaySinh;

    public User() {
    }

    public User(String account, String gmail, String password, String ten, String ngaySinh) {
        Account = account;
        Gmail = gmail;
        Password = password;
        Ten = ten;
        NgaySinh = ngaySinh;
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
