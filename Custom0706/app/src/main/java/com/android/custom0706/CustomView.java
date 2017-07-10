package com.android.custom0706;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 김태훈 on 2017-07-06.
 */

public class CustomView extends View {

    TextView tv;
    MainActivity cnxt;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cnxt = (MainActivity)context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        tv = new TextView(cnxt);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setText("안녕 여기는 ITPANGPANG!");

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(lp);
        ((RelativeLayout)this.getParent()).addView(tv);

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cnxt, "터치!", Toast.LENGTH_LONG).show();
            }
        });

        Paint MyPaint = new Paint();
        MyPaint.setStrokeWidth(10f);
        MyPaint.setStyle(Paint.Style.STROKE);
        MyPaint.setColor(Color.WHITE);
        Path path = new Path();
        path.moveTo(450, 500);
        path.lineTo(450, 500);
        path.lineTo(400, 600);
        path.lineTo(300, 600);
        path.lineTo(400, 700);
        path.lineTo(350, 800);
        path.lineTo(450, 700);
        path.lineTo(550, 800);
        path.lineTo(500, 700);
        path.lineTo(600, 600);
        path.lineTo(500, 600);
        path.close();
        canvas.drawPath(path, MyPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      switch(event.getAction()){
          case MotionEvent.ACTION_DOWN :
              tv.setX(event.getX());
              tv.setY(event.getY());
              break;
      }

        return true;
    }
}
