package com.android.rouletteapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity  implements Serializable{

    private EditText edit_cnt1, edit2;
    private Button home_btn1, home_btn2;
    private static int RES_CODE = 0;
    private ScrollView scroll_menu;
    private LinearLayout line_1;
    private int p_count;
    /*private ArrayList<EditText> res_text_arr;*/
    private HashMap<String, String> p_map_arr;

    private int EDIT_RES_1 = 354556556;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edit_cnt1 = (EditText) findViewById(R.id.edit_cnt1);

        home_btn1 = (Button) findViewById(R.id.home_btn1);
        home_btn2 = (Button) findViewById(R.id.home_btn2);


        scroll_menu = (ScrollView) findViewById(R.id.scroll_menu);
        line_1 = (LinearLayout) findViewById(R.id.line_1);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);

        home_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_count = Integer.parseInt(String.valueOf(edit_cnt1.getText()));

                for(int i = 0 ; i < p_count ; i ++) {
                    EditText edit = new EditText(getApplicationContext());
                    edit.setId(5000+i);
                    edit.setLayoutParams(lp);
                    edit.setText("s"+i);
                    line_1.addView(edit);
                }
                downKeyboard(getApplicationContext(), edit_cnt1);
            }
        });

        home_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* res_text_arr = new ArrayList<EditText>();*/
                p_map_arr = new HashMap<String, String>();
                for(int i = 0 ; i < p_count ; i ++) {
                    EditText edit_arr = (EditText) findViewById(5000+i);
                    if ( edit_arr.getText().toString().length() == 0 ) {
                    //공백일 때 처리할 내용
                        return;
                    } else {
                    //공백이 아닐 때 처리할 내용
                     String  str1 =   edit_arr.getText().toString();
                        p_map_arr.put(String.valueOf(i), str1);
                    }
                }
                Intent intent = new Intent(getApplicationContext(),RotateActivity.class);
                intent.putExtra("p_count", p_count);
                intent.putExtra("edit_res",p_map_arr);
                startActivityForResult(intent,RES_CODE);
            }
        });



    }


    public static void downKeyboard(Context context, EditText editText) {

        InputMethodManager mInputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);

        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }



}
