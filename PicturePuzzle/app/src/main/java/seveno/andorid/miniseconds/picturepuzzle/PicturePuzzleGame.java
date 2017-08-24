package seveno.andorid.miniseconds.picturepuzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PicturePuzzleGame extends Activity {



    Handler p_1_Handler = new Handler();
    Handler p_Handler = new Handler();
    private PuzzleView puzzleView;
    private static long startTime;
    private static long timeTakenMillis;
    private static long elapsedTime;
    private TextView countdown_view;
    private PuzzleController controller;
    private ProgressBar bar_PuzzleGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_puzzle_game);


        puzzleView = (PuzzleView)this.findViewById(R.id.puzzleView);

        bar_PuzzleGame = (ProgressBar) findViewById(R.id.bar_PuzzleGame);
        countdown_view = (TextView) findViewById(R.id.countdown_view_p);

        if (savedInstanceState == null) {//On first startup, creates the sequence, begins the timer and does some cleanup work.



            controller = new PuzzleController(this);
            controller.setCountdownView(countdown_view);
            controller.startGame();


            //프로그래스바
            bar_PuzzleGame.setProgress(bar_PuzzleGame.getMax());


           /* p_1_Handler.postDelayed(new Runnable()  {
                public void run() {
                    //#명령어
                    Animation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(500);
                    *//*.setVisibility(View.GONE);
                    .setAnimation(animation);*//*
                    p_1_Handler.removeMessages(0);
                }
            }, 4000);*/


                   if(savedInstanceState != null){


                puzzleView.loadInstanceState(savedInstanceState);
            }

            p_Handler.postDelayed(new Runnable()  {
                public void run() {
                    //#명령어
                    //sparty_first.setVisibility(View.VISIBLE);
                    startTime = System.currentTimeMillis();
                    // asyncTask 실행
                    new PuzzleProgressTask().execute(bar_PuzzleGame.getProgress());
                    puzzleView.setVisibility(View.VISIBLE);

                    p_Handler.removeMessages(0);
                }
            }, 4500);

        }
        }

    class PuzzleProgressTask extends AsyncTask<Integer, Integer, Boolean> {
        private boolean isPerformed = false;
        private boolean isCancelled = false;
        boolean bubbleRun = true;

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (int i = params[0]; i >= 0; i--) {
                publishProgress(i);
                SystemClock.sleep(200);
            }
            if (isCancelled) {
                return isCancelled;
            }
            timeTakenMillis = System.currentTimeMillis() - startTime;
            //ViewCompleted(mBubbleGameView);
            //BubbleGameView.GameThread.interrupted()
            return isPerformed;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar_PuzzleGame.setProgress(values[0]);
            if (bar_PuzzleGame.getProgress() == 0) {
                isPerformed = true;
            }
           /* if (bar_PuzzleGame.getProgress() > 0) {

                if (BubbleScore != 0) {

                }
            } else if (bar_BubbleGame.getProgress() == 0) {
                isPerformed = true;
            }*/
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected void onPostExecute(Boolean performed) {
            super.onPostExecute(performed);
            if (performed) {
                //mBubbleGameView.setVisibility(View.GONE);
                // SystemClock.sleep(2000);
                PlayNextGame();
            }
        }
    }

    private void PlayNextGame() {
        //t2.interrupt();
        Intent intent = new Intent(this, FinishPuzzleScreen.class);
        intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.bubbleTime", timeTakenMillis);
        //intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.tscore2", T_score);
        intent.putExtra("seveno.android.miniseconds.BubbleShooter.BubbleGame.elapsedTime", elapsedTime);

        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.puzzle, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_shuffle:
                puzzleView.getPuzzle().shuffle();
                puzzleView.invalidate();
                return true;

            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        puzzleView.saveInstanceState(bundle);
    }


}
