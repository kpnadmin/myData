package com.android.moveballex;

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
 * Created by Administrator on 2017-08-11.
 */

public class GameThread extends Thread {
    Context context;
    SurfaceHolder sHolder;
    boolean isRun = true;
    boolean isWait = false;
    Canvas canvas ;
    int width , height;
    //
    Random ran = new Random();
    // 색을 저장할 배열
    Paint[] paint = new Paint[5];
    // 원 객체를 저장할 배열
    ArrayList<Circle> list = new ArrayList<Circle>();

    //생성자
    public GameThread(Context context, SurfaceHolder sHolder){
        this.context = context;
        this.sHolder = sHolder;
        //원을 그릴 색 만들어서 배열에 저장하기
        initPaint();

    }
    //화면의 폭과 높이를 전달바든ㄴ다
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
        Log.e("*********", "width:"+width+" / height:"+height);
    }
    //빠르게 돌아가는 스레드에서 메소드를 실행하거나 멤버필드가 교환되는 작업을 하면
    //스레드 작업이 깨질수있기 때문에 동기화가 필요하다.
    //스레드의 일시정지 혹은 재시작 하는 메소드
    public void pauseNResume(boolean isWait){
        synchronized (this) {
            this.isWait = isWait;
            notify();
        }
    }

    //스레드 완전 정지하는 메소드
    public void stopForever(){
        synchronized (this) {
            this.isRun = isRun;
            notify();
        }
    }

    @Override
    public void run() {
        while(isRun){//isRun의 초기 값이 true 이므로 기본적으로 무한 루프이다.
            //Canvas 객체 얻어오기
            canvas = sHolder.lockCanvas(); // 화면정보를 다 담을 때까지 화면을 잠군다.
            // 화면에 그리는 작업을 한다 .

            try{
            synchronized (sHolder){
                //
                canvas.drawColor(Color.WHITE);
                //
                for(int i = 0 ; i < list.size(); i++) {
                    //
                    Circle cir = list.get(i);
                    canvas.drawCircle(cir.x, cir.y,
                            5, //반지름
                            paint[cir.color] // 원의 색
                    );
                }
                canvas.drawText("공의수:"+list.size(), 0, 20, paint[0]);
                    // void Canvas.drawText(char[] text, int index, int count, float x, float y, Paint paint)
                //if(ran.nextInt(5)==0)
                    makeCircle();
                    // 원 움직이기
                    moveCircle();
                }

            }finally {
                if(canvas != null){
                    sHolder.unlockCanvasAndPost(canvas);
                }
            }
            //스레드를 일시 정지하기 위해
            if(isWait){
                try{
                    synchronized (this){
                        wait();//notify할때까지 기다린다.
                    }
                }catch (Exception e){

                }
            }//if
        }//while
    }//run
    public void initPaint(){
        // paint 객체를 생성해서 배열에 담기
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
        // 원의 색
        int color = ran.nextInt(5);
        // Circle 객체를 생성해서 배열에 담는다 .
         Circle cir = new Circle(x,y, color, speedX, speedY);
            list.add(cir);
    }
    // circle 객체를 움직이는 메소드
    public void moveCircle(){
        //. 반복문을 돌면서 원 객체 움직이기
        for(Circle cir : list){
            cir.x += cir.speedX;
            cir.y += cir.speedY;
            // 화면을 벗어나지 못하도록 처리
            if(cir.x <= 0 || cir.x >= width){
                // 속도의 부호를 바꾸어서 반대방향으로 움직인다 .
                cir.speedX *= -1;
                // 바뀐 속도로 한번 움직여 준다.
                cir.x += cir.speedX;
            }
            if(cir.y <= 0 || cir.y >=height){
                // 속도의 부호를바꾸어서 반대방햐응로 움직인다.
                cir.speedY *= -1;
                // 바뀐소도롤 한번 움직여 준다.
                cir.y += cir.speedY;

            }

        }
    }
}
