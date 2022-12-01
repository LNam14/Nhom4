package com.example.andoirdduan.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.GioHang.GioHangActivity;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.Login.LoginActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {
    FloatingActionButton gioHang;
    TextView tvUser;
    GridView gvSanPham_user;
    ArrayList<SanPham> arraySanPham_user;
    BottomNavigationView navigationView;
    String strUsername = "";
    String nhoMk = "mua";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.tittle );
        setContentView(R.layout.user_activity);

        gioHang = findViewById(R.id.fab);
        navigationView = findViewById(R.id.bottomNavigationView);
        gvSanPham_user = findViewById(R.id.gvSanPham_user);
        tvUser = findViewById(R.id.tvUserName_userActivity);
        Bundle bundle = getIntent().getExtras();
        if(bundle !=  null){
            strUsername = bundle.getString("hoten");
            tvUser.setText("Hello, "+ strUsername);
        }
        if(checkLoginRemember()<0){
//            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
//            startActivity(intent);
            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT ).show();

        }else if(checkLoginRemember()>0){
            Toast.makeText(this,"Lưu mật khẫu thành công",Toast.LENGTH_SHORT ).show();
            System.out.println("USERNAME"+strUsername);
            tvUser.setText("Hello, "+ strUsername);

        }
        loadData();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getBaseContext(), UserActivity.class);
                        startActivity(home);
                        break;
                    case R.id.search:
                        break;
                    case R.id.fab:
                        Intent insert = new Intent(UserActivity.this, GioHangActivity.class);

                        startActivity(insert);
                        break;
                }
                return false;
            }
        });
        gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                intent.putExtra("dulieu", strUsername);
                startActivity(intent);
            }
        });

    }

    public void loadData() {
        Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham_user = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            arraySanPham_user.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6),
                    cursor.getInt( 7 )));
        }
        UserActivityAdapter adapter = new UserActivityAdapter(UserActivity.this, R.layout.cardview_activity, arraySanPham_user);
        gvSanPham_user.setAdapter(adapter);
    }
    public int checkLoginRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("REMEMBER", false);
        if(chk) {
            strUsername = sharedPreferences.getString("USERNAME", "");

            return 1;
        }
        return -1;

    }
}








