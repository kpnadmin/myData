package seveno.android.miniseconds.AvoidStarGame;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2017-08-07.
 */

public class GameManager extends Thread {

    static final long FPS = 25;

    private KnightView view;

    private boolean running = false;


    public GameManager(KnightView view) {
        this.view = view;
    }
    public void setRunning(boolean run) {
        running = run;
    }


    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.update(ticksPS);
                    /*view.onDraw(c);*/
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {
            }
        }
    }
}
