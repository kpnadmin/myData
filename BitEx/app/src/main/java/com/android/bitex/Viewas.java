package com.android.bitex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 김태훈 on 2017-07-17.
 */

public class Viewas extends View {
    public Viewas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.RED);
        canvas.drawText("hhhhhhhhhhhhhhhhhhi", 300, 300, paint);


    }
}
