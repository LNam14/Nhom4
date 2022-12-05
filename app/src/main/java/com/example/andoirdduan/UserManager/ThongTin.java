package com.example.andoirdduan.UserManager;

public class ThongTin {
    String user,quyen;
    int soDu;

    public ThongTin() {
    }

    public ThongTin(String user, String quyen, int soDu) {
        this.user = user;
        this.quyen = quyen;
        this.soDu = soDu;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }
}
