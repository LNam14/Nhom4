package com.example.andoirdduan.ChiTietHoaDon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.example.andoirdduan.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CTHoaDon extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cthoa_don );
        tabLayout = findViewById( R.id.tab_layout );
        viewPager = findViewById( R.id.view_paper );

        tabLayout.addTab( tabLayout.newTab().setText( "Đã thanh toán" ));
        tabLayout.addTab( tabLayout.newTab().setText( "Chưa thanh toán" ));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL );

        final CTHoaDonAdapter adapter = new CTHoaDonAdapter( getSupportFragmentManager(), this,tabLayout.getTabCount() );
        viewPager.setAdapter( adapter );
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );
    }
}