package com.example.andoirdduan.ChiTietHoaDon;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.andoirdduan.R;

public class CTHoaDonAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTab;
    String tittle[] = {"Đã thanh toán","Chưa thanh toán"};
    public CTHoaDonAdapter(FragmentManager fm, Context context, int totalTab){
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tittle[position];
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                DaThanhToan_Fragment daThanhToan_fragment = new DaThanhToan_Fragment();
                return daThanhToan_fragment;
            case 1:
                ChuaThanhToan_Fragment chuaThanhToan_fragment = new ChuaThanhToan_Fragment();
                return chuaThanhToan_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
