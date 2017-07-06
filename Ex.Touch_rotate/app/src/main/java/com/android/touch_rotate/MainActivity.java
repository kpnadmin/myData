package com.android.touch_rotate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyView mView = new MyView(this);
        setContentView(R.layout.activity_main);

        /*Display defaultDisplay = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
       mView.setW_height(height);
        mView.setW_width(width);*/

        //setContentView(mView);

    }
}
