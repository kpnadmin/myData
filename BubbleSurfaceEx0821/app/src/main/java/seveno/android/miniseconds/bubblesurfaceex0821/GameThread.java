package seveno.android.miniseconds.bubblesurfaceex0821;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 김태훈 on 2017-08-21.
 */

public class GameThread extends Thread {
    SurfaceHolder mHolder;                                    // SurfaceHolder를 저장할 변수
    Context mContext;

    int width, height;
    Bitmap imgBack;
    ArrayList<Bubble> mBall = new ArrayList<Bubble>();              // 큰방울
    ArrayList<SmallBubble> sBall = new ArrayList<SmallBubble>();         // 작은방울

    //-------------------------------------
    //  생성자 
    //-------------------------------------
    public GameThread(SurfaceHolder holder, Context context) {
        mHolder = holder;                                                   // SurfaceHolder 보존
        mContext = context;

        Display display = ((WindowManager) context.getSystemService
                (Context.WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();              // View의 가로 폭
        height = display.getHeight() - 50;     // View의 세로 높이
        imgBack = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bubble_sky);
        imgBack = Bitmap.createScaledBitmap(imgBack, width, height, false);
    }

    //-------------------------------------
    //  비눗방울 만들기  - Touch Event에서 호출
    //-------------------------------------
    public void MakeBubble(int x, int y) {
        boolean flag = false;
        for (Bubble tmp :  mBall) {
            if (Math.pow(tmp.x - x, 2) + Math.pow(tmp.y - y, 2)  <= Math.pow(tmp.rad, 2)) {
                tmp.dead = true;                   // 비눗방울 Touch일 경우 
                flag = true;
            }
        }
        if (flag == false)                              // 비눗방울 Touch가 아니면 비눗방울 생성 
            mBall.add(new Bubble(mContext, x, y, width, height));
    }
    //-------------------------------------
    //  작은  비눗방울 만들기
    //-------------------------------------
    private void MakeSmallBubble(int x, int y) {
        Random rnd = new Random();
        int count = rnd.nextInt(9) + 7;               // 7~15개
        for (int i = 1; i <= count; i++) {
            int ang = rnd.nextInt(360);
            sBall.add(new SmallBubble(mContext, x, y, ang, width, height));
        }
    }
    //-------------------------------------
    //  비눗방울 이동  - run에서 호출
    //-------------------------------------
    public void MoveBubble() {
        // 큰 비눗방울 이동
        for (int i = mBall.size() - 1; i >= 0; i--) {
            mBall.get(i).MoveBubble();
            if (mBall.get(i).dead == true) {
                // 작은 비눗방울을 만들고 큰 것은 터뜨림
                MakeSmallBubble(mBall.get(i).x, mBall.get(i).y); // 작은 방울
                mBall.remove(i);
            }
        }
        // 작은 비눗방울 이동
        for (int i = sBall.size() - 1; i >= 0; i--) {
            sBall.get(i).MoveBubble();
            if (sBall.get(i).dead == true)
                sBall.remove(i);
        }
    }

    //-------------------------------------
    //  Canvas에 그리기
    //-------------------------------------
    public void run() {
        Canvas canvas = null;      // canvas를 만든다
        while (true) {
            canvas = mHolder.lockCanvas();  // canvas를 잠그고 버퍼 할당
            try {
                synchronized (mHolder) {  // 동기화 유지
                    MoveBubble();
                    canvas.drawBitmap(imgBack, 0, 0, null);
                    // 큰 비눗방울
                    for (Bubble tmp : mBall) {
                        canvas.drawBitmap(tmp.imgBubble, tmp.x - tmp.rad,  tmp.y - tmp.rad, null);
                    }
                    // 작은비눗방울
                    for (SmallBubble tmp : sBall) {
                        canvas.drawBitmap(tmp.imgBubble, tmp.x - tmp.rad,  tmp.y - tmp.rad, null);
                    }
                } // sync
            } finally {       // 버퍼 작업이 끝나면 
                if (canvas != null)    // 버퍼의 내용을 View에 전송
                    mHolder.unlockCanvasAndPost(canvas);
            }
        } // while
    } // run

} // GameThread 끝





