package com.example.andoirdduan.Login;

public class Login {
    String username, email, password, hoTen, date;

    public Login() {
    }

    public Login(String username, String email, String password, String hoTen, String date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.hoTen = hoTen;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
