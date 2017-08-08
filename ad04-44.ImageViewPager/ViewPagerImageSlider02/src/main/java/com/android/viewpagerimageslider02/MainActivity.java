package com.android.viewpagerimageslider02;

import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImagePagerAdapter pagerAdapter = null;
    private ViewPager mViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.이미지 데이터 생성
        File [] files = null;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File mydir = new File ( path + "/Pictures" );
        if ( mydir.isDirectory () ) {
            files = mydir.listFiles ();
        }

        // 2.adpater 생성
        pagerAdapter = new ImagePagerAdapter(this, files);

        // 3.pager와 adapter 연결
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
    }

}
