package com.android.watersurfacethreadex;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 김태훈 on 2017-08-10.
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
        Log.d(TouchPlayActivity.TAG, "isInTouchMode(): " + isInTouchMode());
    }
    public TouchPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchPlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // 사용자가 화면을 손가락으로 찍으면, 이 메서드에서 이벤트를 받아 처리한다.
    // View의 default handler
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TouchPlayActivity.TAG, "View.onTouchEvent(): " + event);
        tplay.doTouchEvent(event);
        return true;
    }




    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tplay.start(); // 에니메이션 쓰레드 시작
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        tplay.setSurfaceSize(width, height); // 화면 사이즈를 재조정한다.

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        tplay.stop(); // 에니메이셔 쓰레드 중단
    }
}
