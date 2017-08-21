package seveno.android.miniseconds.bubblesurfaceex0821;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 김태훈 on 2017-08-21.
 */

public class MyGameView extends SurfaceView implements SurfaceHolder.Callback{
    GameThread mThread;
    SurfaceHolder mHolder;
    //-------------------------------------
    //  생성자
    //-------------------------------------
    public MyGameView(Context context) {
        super(context);
    }

    public MyGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        mHolder =holder;
        mThread = new GameThread(holder, context);
        setFocusable(true); // View가 포커스를 받을 수 있도록 설정
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            boolean done = true;
        while(done){
            try{
                mThread.join();
                done = false;
            }catch (InterruptedException e){
                //
            }
        }//while
    }

    //-------------------------------------
    //  onTouch Event
    //-------------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mHolder) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                mThread.MakeBubble(x, y);
            }
        }
        return true;
    }

}
