package com.android.imageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView img_view;
    private Button btn_prev, btn_next;
    private ArrayList<File> sd_imgs;
    private int index = 0 ;
    private TextView txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_view = (ImageView) findViewById(R.id.img_view);
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");



        files.listFiles();

        final Bitmap[] bmps = new Bitmap[files.listFiles().length];
        final String[] strings = new String[5];

        for(int i= 0; i<files.listFiles().length; i++){
            Bitmap bmp =  BitmapFactory.decodeFile(files.listFiles()[i].getAbsolutePath());

            bmps[i] = bmp;
            strings[i] = (i+1) +"/"+files.listFiles().length;

        }


        //String imgpath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/mov01.png";
       /* Bitmap bm = BitmapFactory.decodeFile(imgpath);
        img_view.setImageBitmap(bm);*/

      /*  if(files.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(files.getAbsolutePath() +"/mov01.png");

            img_view.setImageBitmap(myBitmap);
            index =0;

        }
        if(files.listFiles().length>0) {


            for (File data : files.listFiles()) {
                sd_imgs.add(data);
            }
        }*/


        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_next = (Button) findViewById(R.id.btn_next);
        txt1 = (TextView) findViewById(R.id.txt1);
        img_view.setImageBitmap(bmps[index]);
        txt1.setText(strings[index]);



        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index = index -1;
                if(index==-1){
                    index = 4 ;
                }
                txt1.setText(strings[index]);
                img_view.setImageBitmap(bmps[index]);

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index +1;
                if(index==5){
                    index = 0 ;
                }
                txt1.setText(strings[index]);
                img_view.setImageBitmap(bmps[index]);
            }
        });

//





       /* String str_file = "";
        if(files.listFiles().length>0){  // 안에 있는 파일의 갯수가 0보다 클때
            for(File res : files.listFiles()){
                //Log.e("1","name: "+ res.getName());  // 로그에 파일의 이름이 찍힘
                str_file = str_file + "  <파일>"+res.getName()+"\n";
                res_text.setText(str_file);
            }
        }
        break;*/



    }
}
