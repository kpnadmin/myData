package com.android.resolutionex;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    int width;
    int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  tv = (TextView) findViewById(R.id.tv);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
     width = size.x;
        height = size.y;*/

    /*  width = tv.getWidth();
        height = tv.getHeight();*/
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("x => " + width + ", y => " + height);
            }
        }, 100);




    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d("ITPANGPANG","onWindowFocusChanged");
        tv = (TextView) findViewById(R.id.tv);
        width = tv.getWidth();
        height = tv.getHeight();
        Log.d("ITPANGPANG","x => " + width + ", y => "+ height);
        tv.setText("x => " + width + ", y => " + height);
        }


}
