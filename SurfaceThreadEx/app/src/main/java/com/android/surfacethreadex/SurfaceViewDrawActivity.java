package com.android.surfacethreadex;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

public class SurfaceViewDrawActivity extends AppCompatActivity {

    protected static int deviceWidth, deviceHeight;


    public static int getDeviceWidth() {
        return deviceWidth;
    }

    public static void setDeviceWidth(int deviceWidth) {
        SurfaceViewDrawActivity.deviceWidth = deviceWidth;
    }

    public static int getDeviceHeight() {
        return deviceHeight;
    }

    public static void setDeviceHeight(int deviceHeight) {
        SurfaceViewDrawActivity.deviceHeight = deviceHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_surface_view_draw);


        DisplayMetrics disp = getApplication().getResources().getDisplayMetrics();
        deviceWidth = disp.widthPixels;
        deviceHeight = disp.heightPixels;


        StarView mStarView = new StarView(this);
        setContentView(mStarView);


        mStarView.setDeviceWidth(deviceWidth);
        mStarView.setDeviceHeight(deviceHeight);



    }


    public class SurfaceThread extends Thread {

        private SurfaceHolder mThreadSurfaceHolder;
        private StarView mThreadSurfaceView;
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
