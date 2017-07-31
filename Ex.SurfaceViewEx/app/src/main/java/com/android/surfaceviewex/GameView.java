package com.android.surfaceviewex;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 김태훈 on 2017-08-01.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    Context context;
    SurfaceHolder sHolder;
    GameThread gameThread;
    int width, height;



    public GameView(Context context) {
        super(context);
        this.context = context;
        init(); //초기화
    }

    public void init(){
        //
        sHolder = getHolder();
        //
        sHolder.addCallback(this);
        //
        gameThread = new GameThread(context, sHolder);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("*********", "surfaceCreated()");
        try {
            //스레드를 시작시킨다.
            gameThread.start();
        } catch (Exception e) {
            Log.e("********", "스레드 시작 시 에러 발생! 스레드를 다시 생성");
            //에러 발생하면 재시작하기
            restartThread();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e("*********", "surfaceChanged()");
        this.width = width;
        this.height = height;
        gameThread.setScreenSize(width, height);
        Log.e("*********", "width:"+width+" / height:"+height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("*********", "surfaceDestroyed()");
        //에러 없이 스레드를 안전하게 종료하기 위해
        boolean tryJoin = true;
        gameThread.stopForever(); //종료시 에러를 잡기 위한 핵심!
        while(tryJoin){//join이 성공할때까지
            try{
                gameThread.join();
                tryJoin=false;
            }catch (Exception e) {

            }
        }
    }

    //스레드를 재시작하는 메소드
    public void restartThread(){
        //스레드 정지
        gameThread.stopForever();
        //스레드 비우고
        gameThread = null;
        //객체 다시 생성
        gameThread = new GameThread(context, sHolder);
        //화면의 폭과 높이 전달
        gameThread.setScreenSize(width, height);
        //스레드 시작
        gameThread.start();
    }
    //스레드 일시정지하는 메소드
    public void pauseThread(){
        gameThread.pauseNResume(true);

    }//일시 정지된 스레드를 재시작하는 메소드
    public void resumeThread(){
        gameThread.pauseNResume(false);

    }

}
