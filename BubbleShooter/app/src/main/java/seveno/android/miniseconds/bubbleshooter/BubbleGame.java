package seveno.android.miniseconds.bubbleshooter;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.content.Intent;

public class BubbleGame extends AppCompatActivity {

    private ProgressBar bar_BubbleGame;
    private LinearLayout linear_1;
    private BubbleGameView mBubbleGameView;
    private BubbleGameView.GameThread threadExGame;
    private int BubbleScore;
    private int T_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_game);

        bar_BubbleGame = (ProgressBar) findViewById(R.id.bar_BubbleGame);
        linear_1 = (LinearLayout) findViewById(R.id.linear_1);
        //프로그래스바
        bar_BubbleGame.setProgress(bar_BubbleGame.getMax());

        linear_1.bringToFront();
        mBubbleGameView = (BubbleGameView) findViewById(R.id.mBubbleGameView);

        // asyncTask 실행
        new BubbleProgressTask().execute(bar_BubbleGame.getProgress());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( threadExGame != null && threadExGame.isAlive()){
            threadExGame.interrupt();
        }

    }

    class BubbleProgressTask extends AsyncTask<Integer, Integer, Boolean>{
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
           /* ViewCompleted(mBubbleGameView);
            bubbleRun = false;*/

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
        Intent intent = new Intent(this, FinishScreenB.class);
        intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.initialATime", 40000);
        intent.putExtra("seveno.android.miniseconds.bubbleshooter.bubbleGame.numErrors", 0);
               /* intent.putExtra("game.speed.android.speed_number_game.numErrors",numErrors);
                intent.putExtra("game.speed.android.speed_number_game.position",highScorePosition);*/

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


 }

}
