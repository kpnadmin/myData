package com.android.anicanvastest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.os.*;
import android.view.*;
import android.content.Context;
import android.content.res.Resources;
import android.app.*;
import android.graphics.*;
import android.util.*;
import android.widget.*;


public class AniCanvasTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_anicanvastest);
        setContentView(new MyView(this));
    }

    private class MyView extends View {
        private Bitmap image1, image2, image3;

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.LTGRAY);

            Resources r = context.getResources();
            image1 = BitmapFactory.decodeResource(r, R.drawable.pacman01);
            image2 = BitmapFactory.decodeResource(r, R.drawable.pacman02);
            image3 = BitmapFactory.decodeResource(r, R.drawable.pacman03);

        }

        @Override
        protected void onDraw(Canvas canvas) {
          canvas.drawBitmap(image1, 0, 0 , null);
            int w  = image2.getWidth();
            int h = image2.getHeight();

            Rect dst = new Rect(400, 800, 400 + w /2, 800 +h/2);
            canvas.drawBitmap(image2,null, dst, null);

            w = image3.getWidth();
            h= image3.getHeight();

            dst = new Rect(400, 1200, 400 +w /2 , 1200+h / 2);
            canvas.drawBitmap(image3, null, dst, null);

            super.onDraw(canvas);
        }
    }
}
