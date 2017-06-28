package com.android.rouletteapp;

import android.app.Activity;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;

/**
 * Created by Administrator on 2017-06-14.
 */

public class CustomView extends View{
            public CustomView(Context context) {
                super(context);
            }
            public void onDraw(Canvas canvas) {
                Paint Pnt = new Paint();
                Pnt.setColor(Color.BLUE);
                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(100,100,80,Pnt);
            }



}
