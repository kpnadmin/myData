package seveno.android.miniseconds.bubblesurfaceex0821;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by 김태훈 on 2017-08-21.
 */

public class Bubble {

    public int x, y, rad;                      // 좌표, 반지름
    public  Bitmap imgBubble;                 // 비트맵 이미지
    public  boolean dead = false;        // 터뜨림 여부

    private int _rad;                             // 원래의 반지름
    private int sx, sy;                          // 이동 방향 및 속도
    private int width, height;                 // View의 크기
    private Bitmap Bubbles[] = new Bitmap[6]; // 풍선 애니메이션 용 이미지
    private int imgNum = 0;                  // 이미지 번호
    private int loop = 0;                       // 루프 카운터
    private int counter = 0;                   // 벽과 충돌 회수


    private Context mContext;

    //-------------------------------------
    //  생성자
    //-------------------------------------


    public Bubble(Context context, int _x, int _y, int _width, int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        //mContext = context;

        imgBubble = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_1);
        Random rnd = new Random();
        _rad = rnd.nextInt(11) + 20;
        rad = _rad;
        //
        for(int i =0 ;  i <= 3 ; i++){
            Bubbles[i] = Bitmap.createScaledBitmap(imgBubble, _rad*2 + i*2 , _rad*2 + i*2 , false);
        }
        Bubbles[4] = Bubbles[2];
        Bubbles[5] = Bubbles[1];
        imgBubble = Bubbles[0];

        sx = rnd.nextInt(2) == 0 ? -1 : 1;
        sy = rnd.nextInt(2) == 0 ? -2 : 2;
        MoveBubble();
    }
    //-------------------------------------
    //  Move
    //-------------------------------------
    public void MoveBubble() {
      loop ++ ;
        if(loop %3 == 0) {
            imgNum ++;
            if(imgNum > 5) imgNum = 0 ;
            imgBubble = Bubbles[imgNum];

            // 비눗방울의 반지름 설정
            rad = _rad + (imgNum <= 3 ? imgNum : 6 - imgNum) * 2;
        }
        x += sx;
        y += sy;
        if (x <= rad || x >= width - rad) {  // 좌우로 충돌
            sx = -sx;
            x += sx;
            counter++;
        }
if(counter >= 3) dead = true; //벽과 충돌 횟수

    }
}// Bubble
//------------------------------------------------------------
//-------------------------------------
//  작은 방울
//-------------------------------------


