package com.android.surfacethreadex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017-08-10.
 */

public class StarView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceThread thread;
    protected static int deviceWidth, deviceHeight;

    public static int getDeviceWidth() {
        return deviceWidth;
    }

    public static void setDeviceWidth(int deviceWidth) {
        StarView.deviceWidth = deviceWidth;
    }

    public static int getDeviceHeight() {
        return deviceHeight;
    }

    public static void setDeviceHeight(int deviceHeight) {
        StarView.deviceHeight = deviceHeight;
    }

    public StarView(Context context) {
        super(context);
        init();
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
                thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while(retry){
            try {
                thread.join();
                retry =false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void init(){
        getHolder().addCallback(this);
        thread = new SurfaceThread(getHolder(), this);

        setFocusable(true); // makesure we get key events
    }


    public class SurfaceThread extends Thread {

        private SurfaceHolder mThreadSurfaceHolder;
        private StarView mThreadSurfaceView;
        private SurfaceViewDrawActivity MainAct;
        private boolean myThreadRun = false;
        private int x = 0 ;
        private int quadWidth = 100;
        private int quadHeight = 100;

        public SurfaceThread(SurfaceHolder surfaceHolder, StarView starView) {
            this.mThreadSurfaceHolder = surfaceHolder;
            this.mThreadSurfaceView = starView;

        }

        @Override
        public void run() {


            //super.run();


            while(myThreadRun){
                Canvas c = null;
                try {
                    c= mThreadSurfaceHolder.lockCanvas(null);
                    synchronized (mThreadSurfaceHolder){
                        Paint mPaint = new Paint();
                        mPaint.setColor(Color.WHITE);

                        deviceWidth = getDeviceWidth();
                        deviceHeight = getDeviceHeight();

                        c.drawRect(0,0,deviceWidth, deviceHeight, mPaint);
                        mPaint.setColor(Color.RED);

                        c.drawRect(x, x, x - quadWidth, x - quadHeight, mPaint);

                        x += 5;
                        if (x - quadWidth >= deviceWidth) {
                            x = 0;
                        }
                    }
                } finally {
                    if (c != null) {
                        mThreadSurfaceHolder.unlockCanvasAndPost(c);
                    }

                }


            }

        }

        public void setRunning(boolean b){
            myThreadRun = b;
        }


    }




}
