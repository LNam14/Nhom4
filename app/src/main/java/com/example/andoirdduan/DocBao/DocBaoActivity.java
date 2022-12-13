    package com.example.andoirdduan.DocBao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andoirdduan.Home.UserActivity;
import com.example.andoirdduan.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class DocBaoActivity extends AppCompatActivity {
    ListView lvTieuDe;
//    ArrayList<String> arrayTitle, arrayLink, arrayHinh;
    ArrayList<DocBao> mangdocbao;
//    ArrayAdapter adapter;
    Customadapter customadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);
        lvTieuDe = (ListView) findViewById(R.id.listviewTieuDe);
//        arrayTitle = new ArrayList<>();
//        arrayHinh = new ArrayList<>();
        mangdocbao = new ArrayList<DocBao>();
//        arrayLink = new ArrayList<>();
//        adapter = new ArrayAdapter(this, R.layout.row_bao, arrayTitle);
//        lvTieuDe.setAdapter(adapter);

        new ReadRSS().execute("https://ngoisao.vnexpress.net/rss/thoi-trang.rss");

        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DocBaoActivity.this, WebActivity.class);
                intent.putExtra("linkTinTuc", mangdocbao.get(position).link);
                startActivity(intent);
                
            }
        });
    }
    private class ReadRSS extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder connect = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line ="";
                while ((line = bufferedReader.readLine()) != null){
                    connect.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connect.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListdescription = document.getElementsByTagName("description");
            String hinhanh = "";
            String tieuDe = "";
            String link = "";
            for(int i = 0; i < nodeList.getLength();i++){
                String cdata = nodeListdescription.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    hinhanh = matcher.group(1);

                }
                Element element = (Element) nodeList.item(i);
                tieuDe = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                mangdocbao.add(new DocBao(tieuDe,link,hinhanh));
//                arrayTitle.add(tieuDe);
//                arrayLink.add(parser.getValue(element, "link"));
            }
            customadapter = new Customadapter(DocBaoActivity.this, android.R.layout.simple_list_item_1,mangdocbao);
            lvTieuDe.setAdapter(customadapter);
//            adapter.notifyDataSetChanged();
        }
    }
}