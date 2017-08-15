package seveno.android.miniseconds.BubbleShooter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import seveno.android.miniseconds.GameEnding;
import seveno.android.miniseconds.R;

public class BubbleGame extends AppCompatActivity {
    private ProgressBar bar_BubbleGame;
    private LinearLayout linear_1, linear_text;
    private BubbleGameView mBubbleGameView;
    private BubbleGameView.GameThread threadExGame;
    private TextView txt_Bubble_score;
    private int BubbleScore;
    private int T_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_game);
        Intent  intent = getIntent();
        long initialTime =  intent.getLongExtra("seveno.android.miniseconds.BubbleShooter.BubbleGame.initialTime",0);
        int speedy_score =  intent.getIntExtra("seveno.android.miniseconds.BubbleShooter.BubbleGame.tscore",T_score);


      /*  long initialTime = getIntent().getLongExtra("seveno.android.miniseconds.speednumgame.initialTime",0);
        int numErrors = getIntent().getIntExtra("seveno.android.miniseconds.speednumgame.numErrors",0);
        int speedy_score = getIntent().getIntExtra("seveno.android.miniseconds.speednumgame.speedy_score", 0);*/

      /*  T_score = speedy_score;

        txt_Bubble_score = (TextView) findViewById(R.id.txt_Bubble_score);
        txt_Bubble_score.setText(String.valueOf(T_score));
        txt_Bubble_score.setTextSize(20);


        bar_BubbleGame = (ProgressBar) findViewById(R.id.bar_BubbleGame);
        linear_1 = (LinearLayout) findViewById(R.id.linear_1);
        linear_text = (LinearLayout) findViewById(R.id.linear_text);
        //프로그래스바
        bar_BubbleGame.setProgress(bar_BubbleGame.getMax());

        linear_1.bringToFront();
        linear_text.bringToFront();
        mBubbleGameView = (BubbleGameView) findViewById(R.id.mBubbleGameView);*/

    // asyncTask 실행
       // new BubbleProgressTask().execute(bar_BubbleGame.getProgress());
    }

  /* @Override
    protected void onStop() {
        super.onStop();
        if( threadExGame != null && threadExGame.isAlive()){
            threadExGame.interrupt();
        }

    }

    class BubbleProgressTask extends AsyncTask<Integer, Integer, Boolean> {
        private boolean isPerformed = false;
        private boolean isCancelled = false;
        boolean bubbleRun = true;

        @Override
        protected Boolean doInBackground(Integer... params) {

            for (int i = params[0]; i>=0 ; i--){
                publishProgress(i);
                SystemClock.sleep(100);
            }
            if(isCancelled){
                return isCancelled;
            }
           /*//* ViewCompleted(mBubbleGameView);
            bubbleRun = false;/*//*

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
            bar_BubbleGame.setProgress(values[0]);
            if(bar_BubbleGame.getProgress() == 0 ){
                isPerformed  =  true;
            }
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }
        @Override
        protected void onPostExecute(Boolean performed) {
            super.onPostExecute(performed);
            if(performed) {
                PlayNextGame();
            }
        }
    }

    private void PlayNextGame() {
        //t2.interrupt();
        Intent intent = new Intent(this, GameEnding.class);
        intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.initialATime", 0);
        intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.tscore2", T_score);
               /*//* intent.putExtra("game.speed.android.speed_number_game.numErrors",numErrors);
                //intent.putExtra("game.speed.android.speed_number_game.position",highScorePosition);/*//*

        startActivity(intent);
        finish();
    }

    private void ViewCompleted(View view){
        try {
            view.setVisibility(View.GONE);
            BubbleGameView.GameThread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }*/
}
