package com.example.andoirdduan.Search;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andoirdduan.ChiTietSanPham.ChiTietSanPham;
import com.example.andoirdduan.Login.LoadingScreenActivity;
import com.example.andoirdduan.R;
import com.example.andoirdduan.SanPham.SanPham;
import com.example.andoirdduan.SanPham.SanPhamAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DSSP_User extends AppCompatActivity {
    ListView listView;
    List<SanPham> listSP;
    String strUsername = "";
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.dssp_activity );
        listView = findViewById(R.id.lvSanPham);
        Bundle bundle = getIntent().getExtras();
        if(bundle !=  null){
            strUsername = bundle.getString("dulieu");
        }
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SanPham sp = listSP.get( position );
                if(!view.isLongClickable()) {
                    Intent intent1 = new Intent(getApplicationContext(), ChiTietSanPham.class);
                    intent1.putExtra("chitiet", sp);
                    intent1.putExtra("dulieu", strUsername);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search, menu );
        SearchView searchView = (SearchView) menu.findItem( R.id.search ).getActionView();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals( "" )){

                }else{
                    Cursor cursor = LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham where tenSP like '%" + s + "%'");
                    listSP = new ArrayList<SanPham>();
                    while (cursor.moveToNext()) {
                        listSP.add(new SanPham(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getBlob(6),
                                cursor.getInt( 7 )));
                    }
                    SanPhamAdapter_User adapter = new SanPhamAdapter_User( DSSP_User.this, R.layout.row__listview, listSP);
                    listView.setAdapter(adapter);
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }

    public void loadData(){
        Cursor cursor =  LoadingScreenActivity.db.TruyVanTraVe("Select * from SanPham");
        listSP = new ArrayList<SanPham>();
        while (cursor.moveToNext()) {
            listSP.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getBlob(6),
                    cursor.getInt( 7 )));
        }

        SanPhamAdapter_User adapter = new SanPhamAdapter_User( DSSP_User.this, R.layout.row__listview, listSP);
        listView.setAdapter(adapter);
    }
}