package com.android.viewpagerimageslider04;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int image[] ={R.drawable.tab_1,R.drawable.tab_2,R.drawable.tab_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 1. ViewPager 찾기
        ViewPager viewPager =(ViewPager)findViewById(R.id.main_viewpager);

        // 2. Adapter 생성
        MyPagerAdapter myviewPagerAdapter =new MyPagerAdapter(getSupportFragmentManager(), image);

        // 3. ViewPager와 Adapter 연결
        viewPager.setAdapter(myviewPagerAdapter);
    }
}
