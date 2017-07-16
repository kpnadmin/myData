package com.android.mainthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mCount = 0 ;
    TextView mCountTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountTextView = (TextView) findViewById(R.id.mCountTextView);

        Thread countThread = new Thread(){
            @Override
            public void run() {

                // 4초동안 1씩 카운트 한다
                for (int i = 0 ; i < 20 ; i ++ ){
                    mCount ++;
                    //
                    try {
                        Thread.sleep(1000);
                        Log.i("superdroid", "Current Count : " +mCount);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        // 텍스트뷰에 현재까지 카운트된 수를 출력한다.
            mCountTextView.setText("Count : " +mCount);

    }

    public void onClick(View view) {
    }
}
