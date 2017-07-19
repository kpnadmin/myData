package com.android.rouletteapp;

import android.app.Activity;
import android.app.*;
import android.content.*;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017-06-14.
 */

public class CustomView extends View{
    TextView tv;
    RotateActivity cnxt;
    private int p_count;
    //sv = new SomeView(this);
    Context context;
    private Bitmap image; // 이미지


    public int getP_count() {
        return p_count;
    }

    public void setP_count(int p_count) {
        this.p_count = p_count;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cnxt = (RotateActivity) context;
    }

    public CustomView(Context context) {
        super(context);
        // 그림 읽어들이기

    }
    public CustomView(Context context, int p_count) {
        super(context);
        this.p_count = p_count;
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

                if(p_count == 3){
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
                    canvas.save();
                }else if(p_count == 4){
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xffff8800);
                    canvas.drawArc(rect, 0 , 90, true, pnt);
                    // 2
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xffffff00);
                    canvas.drawArc(rect, 90, 90, true, pnt);
                    //
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xff0088ff);
                    canvas.drawArc(rect, 180, 90, true, pnt);
                    //
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(getResources().getColor(R.color.Beige));
                    canvas.drawArc(rect, 270, 90, true, pnt);
        } if(p_count == 3){
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
            canvas.save();
        }else if(p_count == 8){
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(0xffff8800);
            canvas.drawArc(rect, 0 , 45, true, pnt);
            // 2
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.AntiqueWhite);
            canvas.drawArc(rect, 45, 45, true, pnt);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.SteelBlue);
            canvas.drawArc(rect, 90, 45, true, pnt);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.Peru);
            canvas.drawArc(rect, 135, 45, true, pnt);

            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.Maroon);
            canvas.drawArc(rect, 180 , 45, true, pnt);
            // 2
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.Khaki);
            canvas.drawArc(rect, 225, 45, true, pnt);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.BurlyWood);
            canvas.drawArc(rect, 270, 45, true, pnt);
            //
            pnt.setStyle(Paint.Style.FILL);
            pnt.setColor(R.color.Olive);
            canvas.drawArc(rect, 315, 45, true, pnt);
        }else{
                   /* int width = 400;
                    int height = 400;
                    Bitmap bitmap1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Canvas can1 = new Canvas();*/
                    /*can1.setBitmap(bitmap1);
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xffff8800);
                    can1.drawArc(rect, 0 , 72, true, pnt);
                    String rectf1 = "";*/
                    //saveBitmapToJpeg(getContext(), bitmap1, rectf1);
                    //
                     pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xffff8800);
                    canvas.drawArc(rect, 0 , 72, true, pnt);
                    // 2
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xffffff00);
                    canvas.drawArc(rect, 72, 72, true, pnt);
                    //
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(0xff0088ff);
                    canvas.drawArc(rect, 144, 72, true, pnt);
                    //
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(Color.DKGRAY);
                    canvas.drawArc(rect, 216, 72, true, pnt);
                    //
                    pnt.setStyle(Paint.Style.FILL);
                    pnt.setColor(Color.MAGENTA);
                    canvas.drawArc(rect, 288, 72, true, pnt);
                }


            }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        setMeasuredDimension(400, 400);
    }
    public static String saveBitmapToJpeg(Context context,Bitmap bitmap, String name){

        File storage = context.getCacheDir(); // 이 부분이 임시파일 저장 경로

        String fileName = name + ".png";  // 파일이름은 마음대로!

        File tempFile = new File(storage,fileName);

        try{
            tempFile.createNewFile();  // 파일을 생성해주고

            FileOutputStream out = new FileOutputStream(tempFile);

            bitmap.compress(Bitmap.CompressFormat.PNG, 90 , out);  // 넘거 받은 bitmap을 jpeg(손실압축)으로 저장해줌

            out.close(); // 마무리로 닫아줍니다.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile.getAbsolutePath();   // 임시파일 저장경로를 리턴해주면 끝!
    }

}
