package seveno.android.miniseconds.duckcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import seveno.android.miniseconds.R;

public class CardStartActivity extends AppCompatActivity {

    CountDownTimer threeTimer;
    TextView countThree;
    LinearLayout startLinear;
    private static long initialTime;
    private static int card_start_score;
    private static long elapsedTime;
    private int T_score;
    private long final_elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_startcard);

        backPressCloseSystem = new BackPressCloseSystem(this);

        Intent intent = getIntent();

        initialTime = intent.getLongExtra("seveno.android.miniseconds.duckcard.cardstart.initialTime",0);
        card_start_score = intent.getIntExtra("seveno.android.miniseconds.duckcard.cardstart.tscore", 0);
        elapsedTime = intent.getLongExtra("seveno.android.miniseconds.duckcard.cardstart.elapsedTime",0);
        T_score = card_start_score;


      /*  intent.putExtra("seveno.android.miniseconds.duckcard.cardmain.initialTime",initialTime);
        intent.putExtra("seveno.android.miniseconds.duckcard.cardmain.elapsedTime",final_elapsedTime);
        intent.putExtra("seveno.android.miniseconds.duckcard.cardmain.tscore",T_score);*/


        int res_Ok  = intent.getIntExtra("var1",0);


        final Button startBtn = (Button) findViewById(R.id.startBtn);
        countThree = (TextView) findViewById(R.id.countThree);
        startLinear = (LinearLayout) findViewById(R.id.startLinear);
        final int millisInFuture = 3100;

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLinear.setVisibility(View.INVISIBLE);
                countThree.setVisibility(View.VISIBLE);
                threeTimer = new CountDownTimer(millisInFuture,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                        String aa = String.valueOf(millisUntilFinished);
                        if      (aa.length() == 4)
                            countThree.setText(aa.substring(0,1));
                        else if (aa.length() == 3){
                            countThree.setText("시작!");
                            countThree.cancelLongPress();
                        }

                    }
                    @Override
                    public void onFinish() {
                        countThree.setText(String.valueOf("시작!"));
                        Intent intent2 = new Intent(CardStartActivity.this, CardMainActivity.class);
                        intent2.putExtra("seveno.android.miniseconds.duckcard.cardmain.initialTime",initialTime);
                        intent2.putExtra("seveno.android.miniseconds.duckcard.cardmain.elapsedTime",elapsedTime);
                        intent2.putExtra("seveno.android.miniseconds.duckcard.cardmain.tscore",T_score);
                        startActivity(intent2);
                        overridePendingTransition(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                    }
                };
                threeTimer.start();
            }
        });
    }

    private BackPressCloseSystem backPressCloseSystem;
    // 뒤로 가기 버튼 이벤트

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}
