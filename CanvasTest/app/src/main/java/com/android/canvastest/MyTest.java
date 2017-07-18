package com.android.canvastest;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by 김태훈 on 2017-07-18.
 */

public class MyTest extends View {
    public MyTest(Context context) {
        super(context);
        setMinimumWidth(400);
        setMinimumHeight(400);
//camera = new Camera();
    }
    private Camera camera = new Camera();
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        camera.save();
        camera.rotateX(45f);
        camera.applyToCanvas(canvas);

        canvas.drawColor(Color.DKGRAY);
        Paint paint = new Paint();
        paint.setTextSize(24);

//front left wheel
        canvas.save();
        canvas.translate(100f, 100f);//xCtr, yCtr
        paint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 60, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("FL", -10, 10, paint);//string
        canvas.restore();

//front right wheel
        canvas.save();
        canvas.translate(300f, 100f);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 60, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("FR", -10, 10, paint);
        canvas.restore();

//rear left wheel
        canvas.save();
        canvas.translate(100f, 300f);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 60, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("BL", -10, 10, paint);
        canvas.restore();

//rear right wheel
        canvas.save();
        canvas.translate(300f, 300f);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 60, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("BR", -10, 10, paint);
        canvas.restore();

//body
        canvas.save();
        canvas.translate(200f, 200f);
        paint.setColor(Color.RED);
        canvas.drawRect(-100, -100, 100, 100, paint);
       canvas.restore();

        camera.restore();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }





}
