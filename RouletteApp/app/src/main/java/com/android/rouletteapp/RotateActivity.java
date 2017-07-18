package com.android.rouletteapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RotateActivity extends Activity{

    private CustomView img_wheel; // 회전 이미지
    private float init_angle = 0.0f;  //초기각도
    private final int IMG_DP = 300 ; // 이미지 dp
    private Bitmap mBitMap;
    private ImageView pin_marker;
    private Button btn_rotate, btn_back_rotate;
    private FrameLayout frame1;
    LayoutInflater inflater = null;
    private final int RES_OK = 0;
    private int p_count ;
    private LinearLayout line_bottom;

    public int getP_count() {
        return p_count;
    }

    public void setP_count(int p_count) {
        this.p_count = p_count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rotate);
      /*  MainCircle drawCircle = new MainCircle(this);
       */
        Intent intent = getIntent();
        p_count = intent.getIntExtra("p_count", 0);
        CustomView csView = new CustomView(this);
        csView.setP_count(p_count);
        //img_wheel = csView;
        //img_wheel.setP_count(p_count);
        //img_wheel = (CustomView) findViewById(R.id.img_wheel) ;
        //img_wheel.setImageResource(R.drawable.roulette);
        /*mBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.roulette);
        img_wheel.setImageBitmap(onResizeImage(mBitMap));*/

        pin_marker = (ImageView) findViewById(R.id.rpin);
        pin_marker.setImageResource(R.drawable.rpin);


       btn_rotate = (Button) findViewById(R.id.btn_rotate);
  /*      btn_rotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onWheelImage();
            }
        });*/

        CustomView sView = new CustomView(this);

        sView.setDrawingCacheEnabled(true);
        sView.buildDrawingCache();
        Bitmap bmp = sView.getDrawingCache();

        Button btn = (Button) findViewById(R.id.btn_rotate);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // onWheelImage();
            }
        });
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        TextView view_count = new TextView(this);
        view_count.setText(String.valueOf(p_count));
        //view1.setTextSize(FONT_SIZE);
        view_count.setTextColor(Color.BLACK);

        //layout_width, layout_height, gravity 설정
   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        view_count.setLayoutParams(lp);

        //부모 뷰에 추가
        line_bottom.addView(view_count);
        sView.setP_count(p_count);
       // Toast.makeText(getApplicationContext(),String.valueOf(p_count), Toast.LENGTH_SHORT);
      /*  inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.layout_sub1, viewContainer, true);*/

// 커스텀뷰 클래스 불러오기
//        setContentView(R.layout.activity_rotate);

        //이미지 set
      /*
      img_wheel = (ImageView) findViewById(R.id.img_wheel) ;
        pin_marker = (ImageView) findViewById(R.id.rpin);
        pin_marker.setImageResource(R.drawable.rpin);
        //imageView1.setImageResource(R.drawable.roulette) ;
        // 비트맵 이미지를 가져온다.
        mBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.roulette);
        // 비트맵을 imageView에 넣는다.
        img_wheel.setImageBitmap(onResizeImage(mBitMap));


        //버튼 이벤트
        Button btn = (Button) findViewById(R.id.rotate_btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onWheelImage();
            }
        });*/
      //CircleBoard c1 = new CircleBoard(this);

        Button btn_back_rotate = (Button) findViewById(R.id.btn_back_rotate);
        btn_back_rotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(RES_OK, intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    /*
   */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }



    private Bitmap onResizeImage(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        //이미지를 dp로 변경
        Float size = convertDpToPixel(IMG_DP, getApplicationContext());

        Bitmap resized = null ;
        while(height > size.intValue()){
            resized = Bitmap.createScaledBitmap(bitmap, (width * size.intValue())/ height, size.intValue(), true);
            height = resized.getHeight();
            width = resized.getWidth();
        }

        return resized;
    }
    private  int getRandom(int maxNumber){
        return (int)(Math.random()*maxNumber);
    }

    private void onWheelImage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            /*    //회전수를 제어
                // 랜덤 0 ~ 360 + 720 도 회전각
                float fromAngel = getRandom(360)+720 +init_angle;
                    // 초기 시작 각도를 update 한다
                // 시작각 ,종료각 , 자기 원을 그리며 회전 옵션 (animation retrieve_to_self, 0.5f, Animation.retrieve_to_self
                RotateAnimation rAnim = new RotateAnimation(init_angle, fromAngel, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                init_angle = fromAngel;
                // 지속시간 갈수록 느려진다.
                rAnim.setDuration(3000);
                // 애니매이션이 종료된 후 상태를 고정 주는 옵션
                rAnim.setFillEnabled(true);
                rAnim.setFillAfter(true);
                // 회전을 한다 .
                img_wheel.startAnimation(rAnim);*/
            }
        });
    }

}
