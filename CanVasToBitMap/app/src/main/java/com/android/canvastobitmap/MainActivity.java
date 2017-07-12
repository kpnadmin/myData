package com.android.canvastobitmap;

import android.graphics.RectF;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;

import java.io.File;
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
    SomeView sv = null;
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sv = new SomeView(this);
        setContentView(sv);
        //saveView(sv);



    }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event)
    {
        switch( event.getKeyCode() )
        {
            case KeyEvent.KEYCODE_DPAD_CENTER:
                if ( sv != null )
                {
                    saveView( sv );
                    return true;
                }
            default:
        }
        return super.onKeyDown( keyCode, event );
    }
    private void saveView( View view )
    {
        Bitmap  b = Bitmap.createBitmap( view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas( b );
        view.draw( c );
       // File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        FileOutputStream fos = null;
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
          fos = new FileOutputStream(path + "/sample.png");
          /*  fos = new FileOutputStream( "/sdcard/some_view_image_" + System.currentTimeMillis() + ".png" );*/
            if ( fos != null )
            {
                b.compress(Bitmap.CompressFormat.PNG, 100, fos );
                fos.close();
            }
            setWallpaper( b );
        } catch( Exception e )
        {
            Log.e("testSaveView", "Exception: " + e.toString() );
        }
    }
    class SomeView extends View
    {
        public SomeView( Context context )
        {
            super( context );
        }
        public void onDraw( Canvas canvas )
        {
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