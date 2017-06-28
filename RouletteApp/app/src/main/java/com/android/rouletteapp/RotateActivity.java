package com.android.rouletteapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class RotateActivity extends Activity{

    private ImageView img_wheel; // 회전 이미지
    private float init_angle = 0.0f;  //초기각도
    private final int IMG_DP = 300 ; // 이미지 dp
    private Bitmap mBitMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


// 커스텀뷰 클래스 불러오기
//        setContentView(R.layout.activity_rotate);
//
/*        CustomView csView = new CustomView(this); setContentView(csView);
*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        //이미지 set
        img_wheel = (ImageView) findViewById(R.id.img_wheel) ;
        //imageView1.setImageResource(R.drawable.roulette) ;
        // 비트맵 이미지를 가져온다.
        mBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.roulette);
        // 비트맵을 imageView에 넣는다.
        /*img_wheel.setImageBitmap(onResizeImage(mBitMap));*/

        //버튼 이벤트
        Button btn = (Button) findViewById(R.id.rotate_btn);


    }

    private Bitmap onResizeImage(Bitmap bitmap) {
        return onResizeImage(mBitMap);
    }


}
