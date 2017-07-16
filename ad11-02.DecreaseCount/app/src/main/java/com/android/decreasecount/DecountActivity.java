package com.android.decreasecount;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DecountActivity extends AppCompatActivity {
    int mCount = 11 ;
    TextView mCountTextView = null;
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decount);

        mCountTextView = (TextView) findViewById(R.id.txt1);


        // message 를 message Queue에 넣는 역할을 한다.

        Thread countThread = new Thread(){
            @Override
            public void run() {

                // 4초동안 1씩 카운트 한다
                for (int i = 10 ; i < 0 ; i -- ){
                    mCount --;
                    //
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mCountTextView.setText(String.valueOf(mCount));
                            Log.i("superdroid", "Current Count : " +mCount);
                        }
                    });
                }

            }
        };
        // 텍스트뷰에 현재까지 카운트된 수를 출력한다.
        countThread.start();



    }
}
