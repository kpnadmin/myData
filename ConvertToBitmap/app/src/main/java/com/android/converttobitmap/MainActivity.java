package com.android.converttobitmap;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import java.io.FileOutputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CustomView1 sv = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sv = new CustomView1(this);
        setContentView(sv);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(event.getKeyCode()){
            case KeyEvent.KEYCODE_DPAD_CENTER:
                if(sv != null){
                   // saveView(sv);
                    return false;
                }
                default:
        }
        return super.onKeyDown(keyCode, event);
    }

   class CustomView1 extends View{


       public CustomView1(Context context) {
           super(context);
       }

       public void onDraw(Canvas canvas){
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
}
