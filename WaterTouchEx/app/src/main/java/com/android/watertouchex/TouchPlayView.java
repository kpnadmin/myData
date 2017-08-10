package com.android.watertouchex;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017-08-10.
 */

public class TouchPlayView extends SurfaceView implements SurfaceHolder.Callback{

    private TouchPlay tplay;

    public TouchPlayView(Context context) {
        super(context);

        // SurfaceHolder의 콜백을 처리할 클래스로 TouchPlayView를 지정
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        tplay = new TouchPlay(holder, context);

        // TouchPlayView에게 포커스를 지정하여 항상 키 이벤트와 터치 이벤트를
        // 받을 수 있게 설정
        setFocusable(true);
        Log.d(MainActivity.TAG, "isInTouchMode(): " + isInTouchMode());
    }

    public TouchPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchPlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }



}
