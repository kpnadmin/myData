package com.android.implict_intentactivity;

import android.app.SearchManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btn_tel;
    private Button btn_homepage;

    private Button btn_gmap      ;
    private Button btn_gSearch   ;
    private Button btn_sms       ;
    private Button btn_gallery   ;
    private Button btn_picture   ;
     private Uri uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        btn_tel = (Button) findViewById(R.id.btn_tel);
        btn_homepage = (Button) findViewById(R.id.btn_homepage);
        btn_gmap = (Button) findViewById(R.id.btn_gmap);
        btn_gSearch = (Button) findViewById(R.id.btn_gSearch);
        btn_sms = (Button) findViewById(R.id.btn_sms);
        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        btn_picture = (Button) findViewById(R.id.btn_picture);



        btn_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 uri  = Uri.parse("tel://01012345678");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

      btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri) ;
                startActivity(intent);
            }
        });

      btn_gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("http://maps.google.com/maps?q=37.55,126.91");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn_gSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "안드로이드");
                startActivity(intent);
            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = Uri.parse("smsmto:01000001234");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", "The SMS text");
                startActivity(it);

            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "content://media/internal/images/media";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(uri));
                startActivity(i);

            }
        });

       btn_picture.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
               uri = Uri.fromFile(new File("/sdcard/landscape.jpg"));
               i.setDataAndType(uri, "image/jpeg");
               startActivity(i);
           }
       });






    }
}
