package com.android.bitex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Viewas csView = new Viewas(this);
        Canvas canvas = new Canvas();

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.setting);




        Bitmap tempBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), Bitmap.Config.RGB_565);

        canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(bitmap2, 0, 0, null);
        ImageView image = (ImageView)findViewById(R.id.image);
        image.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

    }
}
