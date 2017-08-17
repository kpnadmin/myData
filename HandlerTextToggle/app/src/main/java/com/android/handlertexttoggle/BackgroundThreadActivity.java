package com.android.handlertexttoggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class BackgroundThreadActivity extends AppCompatActivity {
    BackgroundThread backgroundThread;
    TextView myText;
    private boolean myTextOn = true;
    private final MyHandler mHandler = new MyHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_thread);

        myText = (TextView) findViewById(R.id.mytext);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
    }

    // Handler 에서 호출하는 함수
    private void handleMessage(Message msg) {
        if (myTextOn) {
            myTextOn = false;
            myText.setVisibility(View.GONE);
        } else {
            myTextOn = true;
            myText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
        Toast.makeText(this, "onStart()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        boolean retry = true;
        backgroundThread.setRunning(false);

        while (retry) {
            try {
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show();
    }

    public class BackgroundThread extends Thread {

        boolean running = false;

        void setRunning(boolean b) {
            running = b;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendMessage(mHandler.obtainMessage());
            }
        }
    }

    // 핸들러 객체 만들기
    private static class MyHandler extends Handler {
        private final WeakReference<BackgroundThreadActivity> mActivity;
        public MyHandler(BackgroundThreadActivity activity) {
            mActivity = new WeakReference<BackgroundThreadActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BackgroundThreadActivity activity = mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }


}
