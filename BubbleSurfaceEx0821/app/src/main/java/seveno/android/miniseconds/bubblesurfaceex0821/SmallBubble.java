package seveno.android.miniseconds.bubblesurfaceex0821;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by 김태훈 on 2017-08-21.
 */

class SmallBubble {
    public int x, y, rad;                            // 좌표, 반지름
    public  boolean dead = false;            // 터뜨림 여부
    public  Bitmap imgBubble;                       // 비트맵 이미지

    private int width, height;                  // View의 크기
    private int cx, cy;                           // 원의 중심점
    private int cr;                                  // 원의 반지름
    private double r;                             // 이동 각도 (radian)
    private int speed;                            // 이동 속도
    private int num;                               // 이미지 번호
    private int life;                                 // 생명 주기

    //-------------------------------------
    //  생성자
    //-------------------------------------
    public SmallBubble(Context context, int _x, int _y, int ang, int _width, int _height) {
        cx = _x;                            // 중심점
        cy = _y;
        width = _width;
        height = _height;
        r = ang * Math.PI / 180;       // 각도 radian

        Random rnd = new Random();
        speed = rnd.nextInt(5) + 2;            // 속도     : 2~6
        rad = rnd.nextInt(6) + 2;                 // 반지름   : 2~7
        num = rnd.nextInt(6);                    // 이미지  : 0~5
        life = rnd.nextInt(31) + 20; // 20~50

        imgBubble = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_b0 + num);
        imgBubble = Bitmap.createScaledBitmap(imgBubble, rad * 2, rad * 2, false);
        cr = 10;      // 원의 초기 반지름
        MoveBubble();
    }
    //-------------------------------------
    //  MoveBall
    //-------------------------------------
    public void MoveBubble() {
        life--;
        cr += speed;
        x = (int) (cx + Math.cos(r) * cr);
        y = (int) (cy - Math.sin(r) * cr);
        if (x < -rad || x > width + rad ||
                y < -rad || y > height + rad || life <= 0)
            dead = true;
    }

} // class
