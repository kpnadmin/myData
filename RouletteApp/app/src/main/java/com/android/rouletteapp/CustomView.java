package com.android.rouletteapp;

import android.app.Activity;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-06-14.
 */

public class CustomView extends View{
    TextView tv;
    RotateActivity cnxt;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cnxt = (RotateActivity) context;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cnxt = (RotateActivity) context;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
                canvas.drawColor(Color.WHITE);
                //
                Paint pnt = new Paint();
                pnt.setStyle(Paint.Style.STROKE);
                pnt.setStrokeWidth(3);
                pnt.setColor(Color.BLACK);
                pnt.setAntiAlias(true);
                //
                RectF rect = new RectF();
                rect.set(200,200,600,600);
                //
                pnt.setStyle(Paint.Style.FILL);
                pnt.setColor(0xffff8800);
                canvas.drawArc(rect, 0 , 120, true, pnt);
                // 2
                pnt.setStyle(Paint.Style.FILL);
                pnt.setColor(0xffffff00);
                canvas.drawArc(rect, 120, 80, true, pnt);
                //
                pnt.setStyle(Paint.Style.FILL);
                pnt.setColor(0xff0088ff);
                canvas.drawArc(rect, 200, 160, true, pnt);
            }



}
