package com.android.fileio;

import android.content.Context;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    private TextView res_text;
    private  String mSdPath;
    private  EditText edit_main1;
    private  String ext;
    private GridLayout grid1;
    private ConstraintLayout const1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = (Button) findViewById(R.id.btn2);
        btn1 = (Button) findViewById(R.id.btn1);
        res_text = (TextView) findViewById(R.id.res_text);
        edit_main1 = (EditText)findViewById(R.id.edit_main1);
        // 내장 메모리 파일 읽기
        //grid1 = (GridLayout) findViewById(R.id.grid1);
        const1 = (ConstraintLayout) findViewById(R.id.const1);




    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                try {
                    FileOutputStream outfs = openFileOutput("inner_test.txt", Context.MODE_APPEND);
                    edit_main1 = (EditText) findViewById(R.id.edit_main1);
                    String strTemp = edit_main1.getText().toString();
                    outfs.write(strTemp.getBytes());
                    outfs.close();
                    Toast.makeText(getApplicationContext(), "make File!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                try {
                    FileInputStream infs = openFileInput("inner_test.txt"); //파일이 없으면 FileNotFoundException예외
                    byte[] temp = new byte[infs.available()]; //파일길이 측정하다가 예외가 발생하면 IOException예외발새
                    infs.read(temp);
                    String strData = new String(temp);
                    Toast.makeText(getApplicationContext(), strData, Toast.LENGTH_SHORT).show();
                    infs.close();
                    res_text.setText(strData);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "not file exist.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                try {
                    FileOutputStream outfs = openFileOutput("raw_test.txt", Context.MODE_APPEND);
                    edit_main1 = (EditText) findViewById(R.id.edit_main1);
                    String str = edit_main1.getText().toString();
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getApplicationContext(), "writeComplate", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                String data = null;
                InputStream inputStream = getResources().openRawResource(R.raw.raw_test);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i;
                try {
                    i = inputStream.read();
                    while (i != -1) {
                        byteArrayOutputStream.write(i);
                        i = inputStream.read();
                    }
                    data = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                res_text.setText(data);

                break;
            case R.id.btn5:
                String filename = "internal_cache_data"; // cache에 저장될 파일 이름
                String data2 = "Go ahead";                    // internal_cache_data파일에 저장될 내용

                try {
                    File cacheDir = getCacheDir();
                    File cacheFile = new File(cacheDir.getAbsolutePath(), filename);
                    FileOutputStream fos = new FileOutputStream(cacheFile.getAbsolutePath());
                    fos.write(data2.getBytes());
                    fos.close();
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
                //
                break;
            //파일 쓰기
            case R.id.btn6:
                try {
                  String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    FileOutputStream out = new FileOutputStream(path + "/sample.txt");
                    edit_main1 = (EditText) findViewById(R.id.edit_main1);
                    String str1 = edit_main1.getText().toString();
                    out.write(str1.getBytes());
                    out.close();
                } catch (FileNotFoundException e) {
                    res_text.setText("Security Exception");
                } catch (Exception e) {
                    res_text.setText(e.getMessage());
                }
                break;
            //sd 파일 읽기
            case R.id.btn7:

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            try {
                FileInputStream in = new FileInputStream(path + "/sample.txt");
                byte[] txt = new byte[in.available()];
                in.read(txt);
                String strData = new String(txt);
                in.close();
                res_text.setText(strData);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "not exist.", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.btn8:

                String path_c = Environment.getExternalStorageDirectory().getAbsolutePath();

                  File mydir = new File(path_c+"/mydir");
                if(!mydir.isDirectory()){
                    mydir.mkdir();
                }
                else {
                    Toast.makeText(MainActivity.this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
                }break;
            case R.id.btn9:
                String path_in = Environment.getExternalStorageDirectory().getAbsolutePath();
                //String dirPath = "/sdcard/android/data";
                try {
                   File mydir2 = new File(path_in + "/mydir");
                    if(mydir2.isDirectory())
                        mydir2.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //File file = new File(dirPath+"/res");
                break;
            case R.id.btn10:

                File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");




                String str_file = "";
                if(files.listFiles().length>0){  // 안에 있는 파일의 갯수가 0보다 클때
                    for(File res : files.listFiles()){
                        //Log.e("1","name: "+ res.getName());  // 로그에 파일의 이름이 찍힘
                        str_file = str_file + "  <파일>"+res.getName()+"\n";
                        res_text.setText(str_file);
                    }
                }
                break;
            default:
                InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(edit_main1.getWindowToken(), 0);
                break;

        }
    }

    protected void hideSoftKeyboard(View view) {
        InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
