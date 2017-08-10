package com.android.watersurfacethreadex;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by 김태훈 on 2017-08-10.
 */

public class TouchPlay implements Runnable {

    boolean onGame, onPause;
    private int canvasW, canvasH;

    private Paint backPaint;
    private RectF backRect;
    private Bitmap backImg;

    private TouchEffect[] effectPool; // Pool 배열
    private Random random = new Random();

    private SurfaceHolder mSurfaceHolder;
    private Context mContext;
    private Resources mRes;

    Thread mThread;


    public TouchPlay(SurfaceHolder surfaceHolder, Context context) {
        mSurfaceHolder = surfaceHolder;
        mContext = context;
        mRes = context.getResources();

        // 배경화면을 칠할 때 사용할 Paint 생성 및 색상지정
        backPaint = new Paint();
        backPaint.setAntiAlias(true);
        backPaint.setARGB(255, 0, 0, 0);
        // 배경화면을 칠할 때 사용할 Rectangle 생성
        backRect = new RectF(0, 0, 0, 0);
        // 배경화면을 칠할 때 사용할 이미지 생성
        backImg = BitmapFactory.decodeResource(mRes, R.drawable.earthrise);

        initTouchEffectPool();

    }

    // 화면 사이즈가 변경되면 배경화면 크기도 조절해 준다.
    public void setSurfaceSize(int width, int height) {
        synchronized (mSurfaceHolder) {
            canvasW = width;
            canvasH = height;
            // 기존 배경용 Rectangle을 화면 크기에 맞게 조정한다.
            backRect.set(0, 0, canvasW, canvasH);
            // 기존 배경이미지를 화면 크기에 맞게 조정한다.
            backImg = Bitmap.createScaledBitmap(backImg, width, height, true);

        }
    }

    // 에니메이션 시작
    public void start() {
        onGame = true;
        mThread = new Thread(this);
        mThread.start();
    }

    // 에니메이션 종료
    public void stop() {
        onGame = false;
        resume();
        // thread.interrupt();
    }

    // 에니메이션 일시중지
    public void pause() {
        onPause = true;
    }

    // 에니메이션 일시중지 해제
    public synchronized void resume() {
        onPause = false;
        notifyAll();
    }


    @Override
    public void run() {
        while (onGame) {
            Canvas c = null;
            try {
                countEffectFrame();

                // canvas를 얻어온다. 이때 얻어온 canvas 는 다른 객체에서 조작 할수 없고 여기서만 조작 가능하도록 lock 처리 된다.
                c = mSurfaceHolder.lockCanvas(null);
                //
                // synchronized (mSurfaceHolder) {
                doDraw(c);
                // }

                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null)
                    // Paint작업이 끝났으면 Canvas를 풀어주고, 화면을 갱신한다.
                    // 주의할 점은 Exception이 발생해도 Canvas lock이 풀어지도록
                    // finally안에서 이 메서드를 호출해야 안전하다.
                    mSurfaceHolder.unlockCanvasAndPost(c);
                // 애니메이션 종료
                if (!onGame) {
                    break;
                }
                // 애니메이션 일시중지
                // wait pool로 들어가서, notifyAll()에 의해 부활될때까지 손가락 빨고 있기
                if (onPause) {
                    try {
                        synchronized (this) {
                            wait();
                        }
                    } catch (Exception e2) {

                    }
                }
                // wait pool에서 겨우 나왔는데, 사용자가 변심해서 아예 에니메이션을
                // 종료시켰는지 재확인
                if (!onGame)
                    break;
            }
        }
    }

    boolean doKeyDown(int keyCode, KeyEvent msg) {
        synchronized (mSurfaceHolder) {
            return false;
        }
    }

    boolean doKeyUp(int keyCode, KeyEvent msg) {
        synchronized (mSurfaceHolder) {
            return false;
        }
    }

    // 터치 이벤트가 눌린 좌표에 물방울 객체를 찍어준다.
    boolean doTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TouchEffect effect = getEffect();

            if (effect != null) {
                effect.set(event.getX(), event.getY());
                return true;
            }

        }
        return false;
    }

    // 넉넉하게 50개의 Effect 객체를 미리 만들어 놓고, pool에 저장해 놓는다
    // 1초안에 50개 이상의 터치를 찍는다면 당신은 인간이 아닌 외계인;;;
    private void initTouchEffectPool() {
        effectPool = new TouchEffect[50];
        for (int i = 0; i < effectPool.length; i++) {
            effectPool[i] = new TouchEffect();
        }
    }

    // Pool 에서 사용가능한 Effect 를 반환해준다 .
    private TouchEffect getEffect() {
        for (int i = 0; i < effectPool.length; i++) {
            if (!effectPool[i].alive) {
                return effectPool[i];
            }
        }
        return null;
    }

    private void countEffectFrame() {
        for (int i = 0; i < effectPool.length; i++) {
            if (effectPool[i].alive) {
                effectPool[i].countFrame();
            }
        }
    }

    public void doDraw(Canvas canvas) {
        // 배경화면 칠하기
        // 배경화면으로 이미지를 그리고 싶은 경우 아래의 주석을 푼다.
        canvas.drawRect(backRect, backPaint);
         canvas.drawBitmap(backImg, 0 , 0 , null);
        // pool에 있는 Effect 객체들을 화면에 그린다.
        for (int i = 0; i < effectPool.length; i++) {
            effectPool[i].doDraw(canvas);
        }
    }


    class TouchEffect {
        final int[][] COLOR_EFFECT = {
                //white
                {0xff, 0xff, 0xff, 0xff},
                // green
                {0xff, 0x68, 0xfc, 0x06},
                //red
                {0xff, 0xfa, 0x0a, 0x1b},
                //blue
                {0xff, 0x33, 0x66, 0xff}
        };
        boolean alive;

        private int[] color;
        private float x, y;
        private int maxFrame = 7;
        private int cntFrame = 1;

        private Paint touchPaint;

        TouchEffect() {
            touchPaint = new Paint();
            touchPaint.setAntiAlias(true);
        }

        private void set(float x, float y) {
            alive = true;
            // 임의적 색상을 지정한다 .
            int idx = random.nextInt(COLOR_EFFECT.length);
            color = COLOR_EFFECT[idx];

            this.x = x;
            this.y = y;
            cntFrame = 0;
        }

        // 물방울 크기를 키운다. 더이상 키울수 없으면 소멸 (alive = false)
        void countFrame() {
            if (cntFrame > maxFrame) alive = false;
            else cntFrame++;
        }

        void doDraw(Canvas canvas) {
            if (!alive) return;
            // 지정된 색상과 크기로 물방울 그리기
            //  setARGB의 인자값은 순서대로, alpha, red, green,blue임
            touchPaint.setARGB(color[0], color[1], color[2], color[3]);
            canvas.drawCircle(x,y,10 * cntFrame, touchPaint );


            // 물방울의 가운데는 검은색으로 처리
            touchPaint.setARGB(255, 0, 0, 0);
            canvas.drawCircle(x, y, (10 * cntFrame) - (2 * cntFrame), touchPaint);
        }
    }
}
