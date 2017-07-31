package com.android.surfaceviewex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.graphics.Paint.Style;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 김태훈 on 2017-08-01.
 */

public class GameThread extends Thread {
    Context context;
    SurfaceHolder sHolder;
    boolean isRun = true;
    boolean isWait = false;
    Canvas canvas;
    int width, height;
    //
    Random ran = new Random();
    //
    Paint[] paint = new Paint[5];
    //
    ArrayList<Circle> list = new ArrayList<Circle>();

    public GameThread(Context context, SurfaceHolder sHolder) {
        this.context = context;
        this.sHolder = sHolder;
        //
        initPaint();
    }
    //화면의 폭과 넓이를 전달
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
        Log.e("*********", "width:"+width+" / height:"+height);
    }
    // 동기화
    public void pauseNResume(boolean isWait){
            synchronized (this){
                this.isWait = isWait;
                notify();
            }
    }
    // 스레드 완전 정지
    public void stopForever(){
        synchronized (this){
            this.isRun = isRun;
            notify();
        }
    }
    //

    @Override
    public void run() {
        /// /super.run();
        while(isRun){
            //캔바스 객체 얻어오기
            canvas = sHolder.lockCanvas();

            try {
                //
                synchronized (sHolder) {
                    // 반복적인 그리기 작업
                    canvas.drawColor(Color.WHITE);
                    // 반복문
                    for (int i = 0; i < list.size(); i++) {
                        //해당 인덱스의 원객체를 얻어온다.
                        Circle cir = list.get(i);
                        canvas.drawCircle(cir.x, cir.y, //원의 위치
                                5,  //반지름
                                paint[cir.color]); //원의 색
                    }
                    canvas.drawText("공의수:" + list.size(), 0, 20, paint[0]);
                    //
                    makeCircle();
                    //원움직이기
                    moveCircle();
                }
            }finally {
                if(canvas!=null)//실제로 화면을 그리는 곳(while을 돌면서 화면을 덧그리기 때문에 invalidate가 필요하지 않다)
                    //잠근 화면을 풀고 작업한canvas 객체를 전달해서 화면을 그린다.
                    sHolder.unlockCanvasAndPost(canvas);
            }
            if(isWait){ //isWait의 초기값은 false 이다
                try {
                    synchronized (this) {
                        wait(); //notify할때까지 기다린다.(일시정지)
                    }
                } catch (Exception e) {}
            }//if

        }//while

    }

    //페인트 객체를 생성해서 배열에 담는 메소드
    public void initPaint(){
        //paint 객체 생성해서 배열에 담기
        Paint p1 = new Paint();
        p1.setColor(Color.DKGRAY);
        p1.setStyle(Style.FILL); // 원을 채우도록
        Paint p2 = new Paint();
        p2.setColor(Color.RED);
        p2.setStyle(Style.FILL); // 원을 채우도록
        Paint p3 = new Paint();
        p3.setColor(Color.GREEN);
        p3.setStyle(Style.FILL); // 원을 채우도록
        Paint p4 = new Paint();
        p4.setColor(Color.BLUE);
        p4.setStyle(Style.FILL); // 원을 채우도록
        Paint p5 = new Paint();
        p5.setColor(Color.YELLOW);
        p5.setStyle(Style.FILL); // 원을 채우도록
        //배열에 담는다.
        paint[0]=p1;
        paint[1]=p2;
        paint[2]=p3;
        paint[3]=p4;
        paint[4]=p5;
    }
    //circle 객체를 만드는 메소드
    public void makeCircle(){
        //원의 위치
        int x = ran.nextInt(width); //화면의 폭 안의 랜덤한 x지점
        int y = ran.nextInt(height); //화면의 높이 안의 랜덤한 y지점
        //원의 초기 속도
        int speedX = ran.nextInt(10)-5;
        int speedY = ran.nextInt(10)-5;
        //원의 색
        int color = ran.nextInt(5);
        //Circle 객체를 생성해서 배열에 담는다
        Circle cir = new Circle(x, y, color, speedX, speedY);
        list.add(cir);
    }
    //circle객체를 움직이는 메소드
    public void moveCircle(){
        //반복문 돌면서 원 객체 움직이기
        for(Circle cir : list){
            cir.x += cir.speedX;
            cir.y += cir.speedY;
            //화면을 벗어나지 못하도록 처리
            if(cir.x <= 0 || cir.x >= width){
                //속도의 부호를 바꾸어서 반대방향으로 움직인다.
                cir.speedX *= -1;
                //바뀐 속도로 한번 움직여 준다.
                cir.x += cir.speedX;
            }
            if(cir.y <= 0 || cir.y >= height){
                //속도의 부호를 바꾸어서 반대방향으로 움직인다.
                cir.speedY *= -1;
                //바뀐 속도로 한번 움직여 준다.
                cir.y += cir.speedY;
            }
        }
    }


}
