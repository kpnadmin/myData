package seveno.android.miniseconds.SpeedNumGame;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import seveno.android.miniseconds.R;

public class SpeedyNumPlay extends AppCompatActivity implements View.OnClickListener {

    private static final int ERROR_PENALTY_SECONDS = 5;
    private static Sequence sequence;
    private static long startTime;
    private static long timeTakenMillis;
    private static boolean timerRunning;
    private static TextView timerTextView;
    private TextView txtView;
    private static TextView errorsMadeTextView;
    private static int numErrors = 0;
    private static Handler h2 = new Handler();
    private static Runnable run;
    private TextView txt_speedy_time;
    private static Thread t;
    private Integer[] speedyNumBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speedy_num_play);

        txt_speedy_time = (TextView) findViewById(R.id.txt_speedy_time);

        speedyNumBtn = new Integer[9];

        if (savedInstanceState == null) {//On first startup, creates the sequence, begins the timer and does some cleanup work.
            sequence = new Sequence(getIntent().getIntExtra("seveno.android.miniseconds.speednumgame.currentGameType", 0));
            startTime = System.currentTimeMillis();
            numErrors = 0;
            timerRunning = true;

            //첫 시작한 현재시간
            final long start = System.currentTimeMillis();
            //시간포맷팅을 위한 포맷설정
            final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
            run = new Runnable() {


                @Override
                public void run() {



                    /*timeTakenMillis = System.currentTimeMillis() - startTime;
                    timerTextView.setText("Time: "+(convertToMinutesAndSeconds(timeTakenMillis)));
                    h2.postDelayed(this, 500);*/
                }
            };
        }



           /* t = new Thread(new Runnable() {
                public void run() {
                    //첫 시작한 현재시간
                    final long start = System.currentTimeMillis();
                    //시간포맷팅을 위한 포맷설정
                    final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");

                    while (!(t.isInterrupted())) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //쓰레드가 돌때마다 계속 현재시간 갱신
                                long end = System.currentTimeMillis();
                                //진행된시간을 계산하여 포맷에 맞게 TextView에 뿌리기
                                txt_speedy_time.setText(sdf.format(end - start).substring(0, 8));
                            }
                        });
                        //0.01초마다 Thread돌리기
                        SystemClock.sleep(10);
                    }
                }
            });
            t.start();*/


        addSequenceToButtons();

       // setupActionBar();
        //addSequenceToButtons();
      /*  if(timerRunning){
            h2.postDelayed(run, 0);
        } else {
            timerTextView.setText(convertToMinutesAndSeconds(timeTakenMillis));
        }*/

    }
    //On button click, check if it is correct (If not then increase error count). If correct then make the button unpressable and translucent,
    //then check if all buttons have been correctly pressed. If they have then update the saved high scores if necessary. Start the finish screen.
    public void buttonClick(View v) throws InterruptedException{
        if(sequence.isCorrect(Integer.parseInt((String)v.getTag()))){
            ((Button)v).setAlpha((float)0.2);
            ((Button)v).setClickable(false);
            if(sequence.allCorrect()){
                h2.removeCallbacks(run);
                timerRunning = false;
                long finalTime = timeTakenMillis + (numErrors*ERROR_PENALTY_SECONDS*1000);
               /* int highScorePosition = findHighScorePosition(finalTime);
                if(highScorePosition >= 1 && highScorePosition <= 10){
                    updateHighScores(highScorePosition, finalTime);
                }
                Intent intent = new Intent(this, FinishScreen.class);
                intent.putExtra("game.speed.android.speed_number_game.initialTime",timeTakenMillis);
                intent.putExtra("game.speed.android.speed_number_game.numErrors",numErrors);
                intent.putExtra("game.speed.android.speed_number_game.position",highScorePosition);
                startActivity(intent);
                finish();*/
            }
        } else {
            numErrors++;
            errorsMadeTextView.setText("Errors: "+numErrors);
        }
    }



    private void setupActionBar(){
        ActionBar actionBar = getActionBar();
        timerTextView = new TextView(this);
        timerTextView.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams timerTextViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        timerTextViewParams.weight = 1;
        timerTextViewParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        LinearLayout actionBarLayout = new LinearLayout(this);
        actionBarLayout.addView(timerTextView,timerTextViewParams);
        ActionBar.LayoutParams actionBarLayoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBarLayout.setLayoutParams(actionBarLayoutParams);
        actionBar.setCustomView(actionBarLayout);
        actionBar.setDisplayShowCustomEnabled(true);

    }

    public String convertToMinutesAndSeconds(long toConvert){
        int seconds = (int) (toConvert / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;
        String minutesAndSeconds = String.format(Locale.ENGLISH,"%d:%02d", minutes, seconds);
        return minutesAndSeconds;
    }

    private void addSequenceToButtons(){
        for(int i = 1; i<=9 ; i++){
         String buttonName ="SpeedyBtn_" + i ;
            Button currentButton = (Button) findViewById(getResources().getIdentifier(buttonName, "id",this.getPackageName()));
          int buttonSequenceNumber = sequence.getIntegerAt(i-1);
            currentButton.setText(""+buttonSequenceNumber);
            currentButton.setTag(""+buttonSequenceNumber);
            if(sequence.isDone(sequence.getIntegerAt(i-1))){
                currentButton.setAlpha((float)0.2);
                currentButton.setClickable(false);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
