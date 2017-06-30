package com.android.draw_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainView mainView = new MainView(this);
        setContentView(mainView);
    }

    protected class MainView extends View {
        public MainView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas){
            canvas.drawColor(Color.WHITE);
            //
            Paint pnt = new Paint();
            pnt.setStyle(Paint.Style.STROKE);
            pnt.setStrokeWidth(3);
            pnt.setColor(Color.BLACK);
            pnt.setAntiAlias(true);
            //
            RectF rect = new RectF();
            rect.set(57,57,183,183);


            //
            rect.set(30, 510, 210, 690);
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
            /*pnt.setStyle(Paint.Style.STROKE);
            pnt.setColor(Color.BLACK);
            canvas.drawArc(rect, 200 , 160, true, pnt);
*/
        }



    }
}
