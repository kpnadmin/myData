package com.android.mainthread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int      mCount = 0;
    TextView mCountTextView = null;
    Handler  mHandler = null;

    @Override
    protected void onCreate( Bundle savedInstanceState )  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountTextView = (TextView) findViewById(R.id.textView1);

        mHandler = new Handler(); // message 를 MessageQueue 에 넣는다.

        Thread countThread = new Thread(){
            @Override
            public void run() {
                // ① 10초 동안 1씩 카운트한다.
                for ( int i = 0 ; i < 20 ; i ++ )  {
                    mCount ++;

                    // ② 현재 카운트된 값을 로그로 출력한다.
                    Log.i("superdroid", "Current Count : " + mCount);
                    try {
                        Thread.sleep(1000); // 1초 동안 멈춤.

                        // 실행할 코드를 만든다.
                        Runnable message = new Runnable(){

                            @Override
                            public void run() {
                                // ③ 텍스트뷰에 현재까지 카운트된 수를 출력한다.
                                mCountTextView.setText( String.valueOf( mCount ) );
                            }
                        };

                        // 메인스레드의 MessageQueue에 던진다.
                        mHandler.post( message );

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        countThread.start();
    }

    public void onClick(View view) {
        // ③ 텍스트뷰에 현재까지 카운트된 수를 출력한다.
        mCountTextView.setText( String.valueOf( mCount ) );
    }
}
