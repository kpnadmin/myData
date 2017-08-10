package com.android.customthread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 김태훈 on 2017-08-10.
 */

public class RobotView extends View implements Runnable {
    private Drawable image;

    int viewWidth,  viewHeight;
    int imageWidth,  imageHeight;
    int x, y;


    public RobotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        image = this.getResources().getDrawable(R.drawable.newrobot);
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        viewWidth = this.getWidth();
        viewHeight = this.getHeight();

        imageWidth = image.getIntrinsicWidth();
        imageHeight = image.getIntrinsicHeight();

        x = viewHeight / 2 - imageWidth /2;
        y = viewHeight - imageHeight;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        image.setBounds(x,y,x+imageWidth,y+imageHeight);
        image.draw(canvas);


    }

    @Override
    public void run() {
        while(true){
            for(int i = 0 ; i < 100; i ++){
                try {
                    Thread.sleep(100);
                    y -=10;
                    this.postInvalidate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
