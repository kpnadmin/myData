package com.android.barandasynctask;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress_1;
    Button btnInc, btnDec;

    SeekBar Seekbar0, Seekbar1, Seekbar2;
    TextView tvSeek;
    Button thread_start;
    Handler handler = new Handler();
    int value = 0; // progressBar 값
    int add = 1; // 증가량, 방향

              TextView    tvSeek1   ;

              TextView    tvSeek2   ;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_1  = (ProgressBar) findViewById(R.id.progress_1);
    btnInc = (Button) findViewById(R.id.btn_Inc);
        btnDec = (Button) findViewById(R.id.btn_Dec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_1.incrementProgressBy(10);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_1.incrementProgressBy(-10);
            }
        });

        final TextView tvSeek = (TextView) findViewById(R.id.SeekText0);
            SeekBar Seekbar0 = (SeekBar) findViewById(R.id.Seekbar0);
        Seekbar0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeek.setText("진행률 : " + progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

         tvSeek1 = (TextView) findViewById(R.id.SeekText1);
         Seekbar1 = (SeekBar) findViewById(R.id.Seekbar1);
         tvSeek2 = (TextView) findViewById(R.id.SeekText2);
         Seekbar2 = (SeekBar) findViewById(R.id.Seekbar2);




        thread_start = (Button) findViewById(R.id.thread_start);


        thread_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new SeekTask().execute(Seekbar1.getProgress(), Seekbar2.getProgress());
            }
        });

    }


    class SeekTask extends AsyncTask<Integer,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Integer... params) {


            return true;
        }

    }

}
