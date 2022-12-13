package com.example.andoirdduan.UserManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andoirdduan.AdminManager.XacNhanNapTien;
import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.DSSPActivity;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamAdapter;
import com.example.andoirdduan.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NapTienActivity extends AppCompatActivity {
    EditText Tien;
    String account = "";
    int tien = 0;
    int tienNap = 0;
    SQLSever sqlSever;
    Button btnNapTien;
    ListView listViewNapTien;
    List<NapTien> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien);
        Tien = findViewById(R.id.edTienNap);
        btnNapTien = findViewById(R.id.btnNapTien);
        listViewNapTien = findViewById( R.id.listViewNapTien );
        sqlSever = new SQLSever(this);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");
        tien = bundle1.getInt("Tien");
        loadData();
        NapTienAdapter adapter = new NapTienAdapter( NapTienActivity.this,R.layout.row_naptien, list );
        listViewNapTien.setAdapter( adapter );
    }

    public void Nap(View view) {
        int tien2 = Integer.parseInt(Tien.getText().toString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String time = String.valueOf( LocalDateTime.now() );
            String phanUng = "";
            tienNap = tien + tien2;
            System.out.println( "Tien2: " + tien2 );
            System.out.println( "Tien: " + tien );
            if(Tien.equals("")){
                Tien.setError("Vui lòng nhập vào số tiền");
            }else {
                LoadingScreenActivity.db.InsertYC(account, time, tien2,phanUng);
                Intent intent = new Intent(getApplicationContext(), XacNhanNapTien.class);
                intent.putExtra("name_user", account);
                intent.putExtra("Tien", tien);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Đã gửi yêu cầu nạp tiền", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), UserManagerActivity.class);
            intent.putExtra("name_user", account);
            intent.putExtra("Tien", tien);
            startActivity( intent );
        }
    }
//    public void Nap(View view) {
//        int tien2 = Integer.parseInt(Tien.getText().toString());
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String time = String.valueOf( LocalDateTime.now() );
//            tienNap = tien + tien2;
//            System.out.println( "Tien2: " + tien2 );
//            System.out.println( "Tien: " + tien );
//            if(Tien.equals("")){
//                Tien.setError("Không được để trống");
//            }else {
//                if (sqlSever.updateNap(account, String.valueOf(tienNap)) > 0) {
//                    Toast.makeText(getApplicationContext(), "Nạp thành công", Toast.LENGTH_SHORT).show();
//                    LoadingScreenActivity.db.InsertNT(account, time, tien2);
//                    Intent intent = new Intent(getApplicationContext(), UserManagerActivity.class);
//                    intent.putExtra("name_user", account);
//                    intent.putExtra("Tien", tien);
//                    startActivity(intent);
//                }
//            }
//        }
//    }
    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from NapTien where user = '" + account + "'");
        list = new ArrayList<NapTien>();
        while (cursor.moveToNext()) {
            list.add(new NapTien(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString( 3 )));
        }
    }
}