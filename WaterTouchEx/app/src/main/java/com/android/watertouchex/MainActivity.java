package com.android.watertouchex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    public static String TAG = "TouchPlay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setTitle(TAG);
        TouchPlayView view = new TouchPlayView(this);
        view.setOnTouchListener(this);
        setContentView(view); // SurfaceView를 메인화면으로 등록


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(MainActivity.TAG, "Activity.onTouch(): " + event);
        return false; // true 설정시 View.onTouchEvent()로 이벤트 전달 안됨
    }
    // Activity의 default handler
    // setContentView()로 View를 등록하지 않을 경우 작동
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(MainActivity.TAG, "Activity.onTouchEvent(): " + event);
        return true;
    }


}
