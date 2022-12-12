package com.example.andoirdduan.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoirdduan.AdminManager.AdminManagerActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.Photo;
import com.example.andoirdduan.PhotoAdapter;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamActivity;
import com.example.andoirdduan.UserManager.UserManagerActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomePage extends AppCompatActivity {
    TextView tvUser;
    FloatingActionButton btnThem;
    ArrayList<SanPham> arraySanPham;
    GridView gridView;
    String strUsername = "";
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;
    BottomNavigationView navigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.homepage_activity );
        btnThem = findViewById( R.id.btnThem_HomePage );
        gridView = findViewById( R.id.gvSanPham );
        navigationView = findViewById( R.id.bottomNavigationView );
        tvUser = findViewById( R.id.tvUserName);


        mListPhoto = getListPhoto();
        viewPager = findViewById(R.id.viewPaper1);
        circleIndicator = findViewById(R.id.circle_indicator1);
        photoAdapter = new PhotoAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        AutoSlideImager();
        Bundle bundle = getIntent().getExtras();
        if(bundle !=  null){
            strUsername = bundle.getString("hoten");
            tvUser.setText("Hello, "+ strUsername);
        }
        if(checkLoginRemember()<0){
            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT ).show();

        }else if(checkLoginRemember()>0){
            Toast.makeText(this,"Lưu mật khẫu thành công",Toast.LENGTH_SHORT ).show();
            System.out.println("USERNAME"+strUsername);
            tvUser.setText("Hello, "+ strUsername);

        }
        loadData();
        navigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getBaseContext(), HomePage.class);
                        startActivity(home);
                        break;
                    case R.id.search:
                        break;
                    case R.id.fab:
                        Intent insert = new Intent(getBaseContext(), SanPhamActivity.class);
                        startActivity(insert);
                        break;
                    case R.id.user:
                        Intent user = new Intent(getBaseContext(), AdminManagerActivity.class);
                        user.putExtra("name_user", strUsername);
                        startActivity(user);
                        break;
                }
                return false;
            }
        });
        btnThem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SanPhamActivity.class);
                startActivity(intent);
            }
        } );

    }
    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6),
                    cursor.getInt( 7 )));
        }
//        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, R.layout.cardview_activity, arraySanPham);
        HomePageAdapter adapter = new HomePageAdapter(HomePage.this,R.layout.cardview_activity, arraySanPham);
        gridView.setAdapter(adapter);
    }
    public int checkLoginRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("REMEMBER", false);
        if(chk){
            strUsername = sharedPreferences.getString("USERNAME","");

            return 1;
        }
        return -1;

    }
    public void chuyenTrang(){
        Intent intent = new Intent(getBaseContext(), DSSPActivity.class );
        startActivity( intent );
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.giay_slide));
        list.add(new Photo(R.drawable.giay_slide2));
        list.add(new Photo(R.drawable.shoes_slide));
        return list;
    }

    private void AutoSlideImager(){
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager == null){
            return;
        }

        //init timer
        if(mTimer == null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if(currentItem<totalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },200,2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
        }
    }

}
