package com.android.handlersendex;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017-08-16.
 */

public class BackThread extends Thread{
    int backValue = 0;
    private Handler mHandler;


    public BackThread(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        while(true){
            Message msg = mHandler.obtainMessage( ) ;   /// 핸들에 전달할 메세지 구조체 받기

            backValue++;
            // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달

            msg.what = 0; msg.arg1 = backValue;

            //mHandler.sendEmptyMessage(0);


            mHandler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end while
    } // end run()


}

