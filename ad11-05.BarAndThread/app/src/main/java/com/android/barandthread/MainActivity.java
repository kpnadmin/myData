package com.android.barandthread;

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
                handler = new Handler();
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() { // Thread 로 작업할 내용을 구현

                        for(int i =  Seekbar1.getProgress(); i <= Seekbar1.getMax(); i=i+2){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                  Seekbar1.setProgress(Seekbar1.getProgress()+2);
                                    tvSeek1.setText("1번 진행률 " + Seekbar1.getProgress()+ "%");
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
                t1.start(); // 쓰레드 시작

                //
                handler = new Handler();
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() { // Thread 로 작업할 내용을 구현

                        for(int i =  Seekbar2.getProgress(); i <= Seekbar2.getMax(); i=i+2){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Seekbar2.setProgress(Seekbar2.getProgress()+2);
                                    tvSeek2.setText("2번 진행률 " + Seekbar2.getProgress()+ "%");
                                }
                            });
                            //1초동안 멈춤
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t2.start(); // 쓰레드 시작
            }
        });





    }
}
