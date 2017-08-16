package com.android.pumpingex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 김태훈 on 2017-08-14.
 */

public class EventSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceThread thread;
    private float initX, initY, radius;
    private boolean drawing = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            radius = (float) Math.sqrt(Math.pow(x - initX, 2) + Math.pow(y - initY, 2));
        } else if (action == MotionEvent.ACTION_DOWN) {
            initX = event.getX();
            initY = event.getY();
            radius = 1;
            drawing = true;
        } else if (action == MotionEvent.ACTION_UP) {
            drawing = false;
            performClick();
        }

        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public EventSurfaceView(Context context) {
        super(context);
        init();
    }

    public EventSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        thread = new SurfaceThread(getHolder(), this);

        setFocusable(true); // make sure we get key events

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }


    public class SurfaceThread extends Thread {

        private SurfaceHolder mThreadSurfaceHolder ;
        private EventSurfaceView mThreadSurfaceView;
        private boolean myThreadRun  = false;
        private int dX = -100 ;

        public SurfaceThread(SurfaceHolder surfaceHolder, EventSurfaceView surfaceView) {
            mThreadSurfaceHolder = surfaceHolder;
            mThreadSurfaceView = surfaceView;
        }
        public void setRunning(boolean b){
            myThreadRun  = b;
        }

        @Override
        public void run() {
            // super.run();
            while(myThreadRun){
                Canvas c = null;
                try {
                    c = mThreadSurfaceHolder.lockCanvas(null);
                    synchronized (mThreadSurfaceHolder){
                        if(drawing){
                            c.drawCircle(initX, initY, radius, paint);
                        }
                    }
                } finally {
                    if( c != null){
                        mThreadSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }

        }
    }






}//
