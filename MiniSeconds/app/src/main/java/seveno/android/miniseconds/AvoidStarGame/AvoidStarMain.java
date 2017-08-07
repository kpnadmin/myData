package seveno.android.miniseconds.AvoidStarGame;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import seveno.android.miniseconds.R;

public class AvoidStarMain extends AppCompatActivity implements View.OnTouchListener{

    private Random Ran_move;
    private int move_X, move_Y;
    private static Thread t2;
    Handler handler_progress2 = new Handler();
    private ProgressBar bar_AvoidStar;
    private static int t2_end_num =0;
    private static long startTime;
    private Intent AvoidIntent ;
    private ImageView img_star_1,img_star_2,img_star_3,img_star_4,img_star_5,img_star_6;
    private ImageView StarimgView[] = new ImageView[6];
    private int sumX, sumY;
    private ArrayList<Integer> idArr1 = new ArrayList<Integer>();

    private KnightView knight;
    private LinearLayout layout_avoidTarget;
    private KnightView knightView ;


/*
    배열 선언 할 때에는 onCreate 메소드 밖에서 해줘야 됨.
            그리고, XML 지정해 줄 때에는, ID를 모아놓은 int 배열을 하나 만들어놓고,

for(int i=0; i < imgView.length(); i++){
        imgView[i] = (ImageView)findViewById(idArr[i]);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AvoidIntent = getIntent();

       // KnightView nView = new KnightView(this);
        layout_avoidTarget = (LinearLayout) findViewById(R.id.layout_avoidTarget) ;

        /*knightView = new KnightView(getApplicationContext());
        layout_avoidTarget.addView(knightView);*/


       /* requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        setContentView(R.layout.activity_avoid_star_main);
        int idArr[] = {
                R.id.img_star_1,
                R.id.img_star_2,
                R.id.img_star_3,
                R.id.img_star_4,
                R.id.img_star_5,
                R.id.img_star_6
        };





        for(int i=0; i < 6; i++){
            StarimgView[i] = (ImageView)findViewById(idArr[i]);
        }
         Random rand3 = new Random();
        Random rand4 = new Random();

        int direct = rand4.nextInt(2);
        int startEnemy = 0;
        //setContentView(new AGameView(this));
        if (savedInstanceState == null) {
            startTime = System.currentTimeMillis();

            Ran_move = new Random();
            move_X = Ran_move.nextInt(800) + 1;
            move_Y = Ran_move.nextInt(480) + 1;

            bar_AvoidStar = (ProgressBar) findViewById(R.id.bar_AvoidStar);
            int temp = 0;

            for (int j = 0 ; j < idArr.length; j++) {
                startEnemy = rand3.nextInt(6);
                if(!idArr1.contains(startEnemy) || (j == 0) ){
                    idArr1.add(startEnemy);
                }else{
                    j--;
                }
            }


          /*  for (int j = 0 ; j < idArr.length; j++) {
                startEnemy = rand3.nextInt(6);
                if(j>=1){
                    temp = -1;
                    for (int k =0; k <idArr1.size(); k++){
                       if(startEnemy == idArr1.get(k)){
                           --j;
                           temp =1;
                           break;
                       }
                    }
                    if(temp == -1){
                    idArr1.add(startEnemy);
                    }
                }else{
                    idArr1.add(startEnemy);
                }
            }*/

       System.out.println(idArr1.toString());
            /*
       

ImageView img = (ImageView)findViewById(R.id.xxxx);

TranslateAnimation ani = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, fromY,
                Animation.RELATIVE_TO_SELF, toY);
    ani.setFillAfter(true); // 애니메이션 후 이동한좌표에
    ani.setDuration(durationMillis); //지속시간

img.startAnimation(ani);
            */
            int[] location = new int[2];
            StarimgView[startEnemy].getLocationOnScreen(location);

            TranslateAnimation anim1 = new TranslateAnimation
                    (location[0],   // fromXDelta
                            location[0],  // toXDelta
                            location[1],    // fromYDelta
                            location[1]+800);// toYDelta

            anim1.setDuration(500);

            TranslateAnimation anim2 = new TranslateAnimation
                    (location[0],   // fromXDelta
                            location[0],  // toXDelta
                            location[1],    // fromYDelta
                            location[1]+200);// toYDelta

            anim2.setDuration(500);


            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                StarimgView[startEnemy].startAnimation(anim1);
                anim1.setFillAfter(true);
                // 세로 모드
            }else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // 가로 모드
                StarimgView[startEnemy].startAnimation(anim2);
                anim2.setFillAfter(true);
        }





    /*        *//*StarimgView[startEnemy];*//*
        if(startEnemy == 0 ) {
            TranslateAnimation anim1 = new TranslateAnimation
                    (0,   // fromXDelta
                            0,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim1.setDuration(2000);
            StarimgView[startEnemy].startAnimation(anim1);
            anim1.setFillAfter(true);
        }else if(startEnemy == 1){
            TranslateAnimation anim2 = new TranslateAnimation
                    (40,   // fromXDelta
                            40,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim2.setDuration(1000);
            StarimgView[startEnemy].startAnimation(anim2);
            anim2.setFillAfter(true);
            }else if(startEnemy == 2){
            TranslateAnimation anim3 = new TranslateAnimation
                    (80,   // fromXDelta
                            80,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim3.setDuration(1000);
            StarimgView[startEnemy].startAnimation(anim3);
            anim3.setFillAfter(true);
        }else if(startEnemy == 3){
            TranslateAnimation anim4 = new TranslateAnimation
                    (120,   // fromXDelta
                            120,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim4.setDuration(1000);
            StarimgView[startEnemy].startAnimation(anim4);
            anim4.setFillAfter(true);
        }else if(startEnemy == 4){
            TranslateAnimation anim5 = new TranslateAnimation
                    (160,   // fromXDelta
                            160,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim5.setDuration(1000);
            StarimgView[startEnemy].startAnimation(anim5);
            anim5.setFillAfter(true);
        }else if(startEnemy == 5){
            TranslateAnimation anim6 = new TranslateAnimation
                    (200,   // fromXDelta
                            200,  // toXDelta
                            0,    // fromYDelta
                            800);// toYDelta
            anim6.setDuration(1000);
            StarimgView[startEnemy].startAnimation(anim6);
            anim6.setFillAfter(true);
        }

          toViewRawXY(StarimgView[startEnemy]);*/

           /* Toast.makeText(getApplicationContext(), sumX+" "+sumY,Toast.LENGTH_SHORT);*/



            bar_AvoidStar.setProgress(bar_AvoidStar.getMax());

            handler_progress2 = new Handler();
            t2 = new Thread(new Runnable() {
                @Override
                public void run() { // Thread 로 작업할 내용을 구현

                    for (int i = bar_AvoidStar.getProgress(); i >= 0; i = i - 1) {
                        if (bar_AvoidStar.getProgress() == 0) {
                            PlayNextGame();
                            break;
                        }
                        handler_progress2.post(new Runnable() {
                            @Override
                            public void run() {
                                bar_AvoidStar.setProgress(bar_AvoidStar.getProgress() - 1);

                            }
                        });
                        //1초동안 멈춤
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //SystemClock.sleep(100);
                    }
                }
            });

            t2.start(); // 쓰레드 시작*/

        }//
    }

    private void PlayNextGame() {
        t2.interrupt();
        Intent intent = new Intent(this, FinishScreenA.class);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.initialTime",40000);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.numErrors",0);
               /* intent.putExtra("game.speed.android.speed_number_game.numErrors",numErrors);
                intent.putExtra("game.speed.android.speed_number_game.position",highScorePosition);*/

        startActivity(intent);
        finish();


    }

    /*
    *
    * */
    public void toViewRawXY(View view){
        View parentView = view.getRootView();
        View ansView = parentView.getRootView();
        sumX = 0;
        sumY = 0;
        boolean chk = false;
        boolean chk2 = false;

        while(!chk){
            sumX = sumX + view.getLeft();
            sumY = sumY + view.getTop();
            view = (View) view.getParent();
            if(parentView == view){
                chk = true;
            }
        }

      /*  while(!chk2){
            sumX = sumX + view.getLeft();
            sumY = sumY + view.getTop();
            view = (View) view.getParent();
            if(ansView == view){
                chk2 = true;
            }
        }*/

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }



}
