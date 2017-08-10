package com.android.watersurfacethreadex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchPlayActivity extends AppCompatActivity implements View.OnTouchListener{

    public static String TAG = "TouchPlay";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_touch_play);
        setTitle(TAG);
        TouchPlayView view = new TouchPlayView(this);
        view.setOnTouchListener(this);
        setContentView(view); // SurfaceView를 메인화면으로 등록
    }
    // 이벤트 전달 순서: OnTouchListener.onTouch -> View.onTouchEvent
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TouchPlayActivity.TAG, "Activity.onTouch(): " + event);
        return false;
    }
    // Activity의 default handler
// setContentView()로 View를 등록하지 않을 경우 작동

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TouchPlayActivity.TAG, "Activity.onTouchEvent(): " + event);
        return true;
    }
}
