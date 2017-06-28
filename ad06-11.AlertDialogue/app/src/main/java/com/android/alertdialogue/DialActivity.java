package com.android.alertdialogue;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DialActivity extends AppCompatActivity {

    private Button btn1 ;
    private Button btn2;
    private Button btn3 ;
    private Button btn4 ;
    private Button btn5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        final EditText edit1 = (EditText) findViewById(R.id.edit1);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);




        edit1.setFocusable(false);
        edit1.setClickable(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialActivity.this);
                dlg.setTitle("제목입니다.");
                dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인", null);
                dlg.show();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialActivity.this);
                dlg.setTitle("제목입니다.");
                dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialActivity.this, "확인을 눌렀네요.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialActivity.this, "닫기를 눌렀네요.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"롤리팝","마시멜로","NouGat"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialActivity.this);
                dlg.setTitle("좋아하는 버젼은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit1.setText(versionArray[which]);
                    }
                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"롤리팝","마시멜로","NouGat"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialActivity.this);
                dlg.setTitle("좋아하는 버젼은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit1.setText(versionArray[which]);
                    }
                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"롤리팝", "마시멜로", "Nougat"};
                final boolean[] checkArray = new boolean[]{true, false, false};
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialActivity.this);
                dlg.setTitle("좋아하는 버젼은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //선택된(true) checkbox만 추출해서 list 로 만든다.
                        List<Integer> trueList = new ArrayList<Integer>();
                        for(int i = 0 ; i < checkArray.length; i++){
                            if(checkArray[i]){
                                trueList.add(i);
                            }
                        }

                        String colors= "";
                        for(int i = 0 ; i < trueList.size(); i++){

                            colors = colors + versionArray[trueList.get(i)];

                            if( i!= trueList.size() -1){
                                colors = colors +",";
                            }else{
                                colors += ".";
                            }
                        }
                        edit1.setText(colors);
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit1.setText("");
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
/*                dlg.setNegativeButton("취소", edit1.setText(""));
                dlg.setPositiveButton("닫기", null);*/
                dlg.show();
            }
        });

    }
}
