package com.example.andoirdduan.UserManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.Database.SQLSever;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RutTienActivity extends AppCompatActivity {
    EditText Tien;
    String account = "";
    int tien = 0;
    int tienNap = 0;
    SQLSever sqlSever;
    Button btnNapTien;
    ListView listViewNapTien;
    List<RutTien> list = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut_tien);
        Tien = findViewById(R.id.edTienRut);
        btnNapTien = findViewById(R.id.btnRutTien);
        listViewNapTien = findViewById( R.id.listViewRutTien );
        sqlSever = new SQLSever(this);
        Bundle bundle1 = getIntent().getExtras();
        account = bundle1.getString("name_user");
        tien = bundle1.getInt("Tien");
        loadData();
        RutTienAdapter adapter = new RutTienAdapter( RutTienActivity.this,R.layout.row_naptien, list );
        listViewNapTien.setAdapter( adapter );
    }

    public void Nap(View view) {
        try {
            int tien2 = Integer.parseInt( Tien.getText().toString() );
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String time = String.valueOf( LocalDateTime.now() );
                tienNap = tien - tien2;
                if(Tien.equals("")){
                    Tien.setError("Không được để trống");
                }else{
                    if(tienNap < 0){
                        Toast.makeText( this, "Số dư không đủ", Toast.LENGTH_SHORT ).show();
                    }else{
                        if (sqlSever.updateNap( account, String.valueOf( tienNap ) ) > 0) {
                        Toast.makeText( getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT ).show();
                        LoadingScreenActivity.db.InsertRT( account, time, tien2 );
                        Intent intent = new Intent( getApplicationContext(), UserManagerActivity.class );
                        intent.putExtra( "name_user", account );
                        intent.putExtra( "Tien", tien );
                        startActivity( intent );
                        }
                    }
                }
            }
        }catch (Exception ex){
            Toast.makeText( this, "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT ).show();
        }
    }
    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from RutTien where user = '" + account + "'");
        list = new ArrayList<RutTien>();
        while (cursor.moveToNext()) {
            list.add(new RutTien(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2)));
        }
    }
}