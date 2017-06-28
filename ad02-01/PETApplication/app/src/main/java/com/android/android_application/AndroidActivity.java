package com.android.android_application;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.android.petapplication.R;

public class AndroidActivity extends AppCompatActivity {


    // 위젯 선언
    private RadioGroup rgp_android = null;
    private Switch switch_start ;
    private LinearLayout android_layout1;
    private Button btn_end;
    private Button btn_first;
    private ImageView img_android;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        //위젯 찾기
        rgp_android = (RadioGroup) findViewById(R.id.rgp_android);
        switch_start = (Switch) findViewById(R.id.switch_start);
        android_layout1 = (LinearLayout) findViewById(R.id.android_layout1);
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_first = (Button) findViewById(R.id.btn_first);
        img_android = (ImageView) findViewById(R.id.img_android);


        // 핸들러 설정
        switch_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == switch_start) {
                    if (isChecked) {
                        android_layout1.setVisibility(View.VISIBLE);
                    } else {
                        android_layout1.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


        rgp_android.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (group == rgp_android) {
                    if (checkedId == R.id.rd_rolly) {
                        img_android.setImageResource(R.drawable.android_rolly);
                        img_android.setVisibility(View.VISIBLE);
                    }else if (checkedId == R.id.rd_marshmello) {
                        img_android.setImageResource(R.drawable.android_marshmello);
                        img_android.setVisibility(View.VISIBLE);
                    } else if (checkedId == R.id.rd_nougat) {
                        img_android.setImageResource(R.drawable.android_nougat);
                        img_android.setVisibility(View.VISIBLE);
                    }else {
                        img_android.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });



        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });

    }
}
