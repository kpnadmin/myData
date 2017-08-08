package seveno.android.miniseconds.tapp_downex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

public class Tapp_DownGame extends AppCompatActivity {
    boolean firstTouch = true;
    private int myScore = 0;
    boolean end_game = false;
    CountDownTimer count_down;
    int SPLASH_TIME = 1250;
    int gameTime;
    long timeLeft;
    TextView countDown;
    TextView countTextView;
    int origTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        overridePendingTransition(R.anim.in,R.anim.out);

        Intent launch_game = getIntent();
        gameTime = launch_game.getIntExtra("gameTime", 0);
        origTime = gameTime/1000;
        timeLeft = (long)gameTime;

        countTextView = (TextView) findViewById(R.id.TextViewCount);
        countDown = (TextView) findViewById(R.id.countdown);
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_screen);

        //START TIMER
        startCountDownTimer(timeLeft);

        layout.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (firstTouch){
                    firstTouch = false;
                    count_down.start();

                }
                if (end_game == false){
                    myScore ++;
                    countTextView.setText ("" + myScore);
                }
                return false;
            }
        });
    }

    public void startCountDownTimer(long run){
        count_down = new CountDownTimer(run, 1000) {

            public void onTick(long millisUntilFinished) {
                countDown.setText("time: "+ millisUntilFinished/1000);
                timeLeft = millisUntilFinished;
            }

            public void onFinish() {
                end_game = true;
                countDown.setText("time's up!");
                countTextView.setText("stop");

                try {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent show_score = new Intent(Tapp_DownGame.this, CurrentScoreActivity.class);
                            show_score.putExtra("myScore", myScore);
                            show_score.putExtra("origTime", origTime);
                            startActivity(show_score);
                            finish();
                        }
                    }, SPLASH_TIME);

                } catch(Exception e){}

            }
        };
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!firstTouch && !end_game){
                countDown.setText("tap to resume");
                countTextView.setText("*");
                myScore--;
                count_down.cancel();
                firstTouch=true;
                startCountDownTimer(timeLeft);
            }

            AlertDialog.Builder alert_box = new AlertDialog.Builder(Tapp_DownGame.this);
            alert_box.setTitle("Leave current game?");

            alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    count_down.cancel();
                    Intent main_menu = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivityForResult(main_menu, 0);
                    finish();
                }
            });

            alert_box.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alert_box.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!firstTouch && !end_game){
            countDown.setText("tap to resume");
            countTextView.setText("*");
            myScore--;
            count_down.cancel();
            firstTouch=true;
            startCountDownTimer(timeLeft);
        }
    }
}
