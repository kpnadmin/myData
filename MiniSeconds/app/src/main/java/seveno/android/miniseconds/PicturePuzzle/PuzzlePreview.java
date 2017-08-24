package seveno.android.miniseconds.PicturePuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import seveno.android.miniseconds.R;

public class PuzzlePreview extends AppCompatActivity {

    private Handler p_1_handler;

    private ImageView sparty_first;
    private TextView txt_puzzle_content;
    private static long prevTime;
    private static long timeTakenMillis;
    private static long elapsedTime;
    private int T_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_preview);


        sparty_first = (ImageView) findViewById(R.id.sparty_first);
        Intent intent = getIntent();
/*
        intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.initialTime",initialTime);
        intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.tscore",T_score);
        intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.elapsedTime",elapsedTime);
        */
        prevTime = intent.getLongExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.initialTime", 0);
        int speedy_score = intent.getIntExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.tscore", T_score);
        elapsedTime = intent.getLongExtra("seveno.android.miniseconds.PicturePuzzle.PuzzlePreview.elapsedTime", elapsedTime);
        T_score = speedy_score;

        // thread start
        p_1_handler = new Handler();
        p_1_handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( getApplicationContext(), PicturePuzzleGame.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzleGame.puzzleTime", timeTakenMillis);
                intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzleGame.tscore2", T_score);
                intent.putExtra("seveno.android.miniseconds.PicturePuzzle.PuzzleGame.elapsedTime", elapsedTime);

                startActivity(intent);
                // 액티비티 이동시 페이드인/아웃 효과를 보여준다. 즉, 인트로
                //    화면에 부드럽게 사라진다.
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                p_1_handler.removeMessages(0);
                finish();
            }
        }, 3000);

    }
}
