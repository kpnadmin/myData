package com.android.audioplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button startBtn, pauseBtn,  restartBtn, stopBtn;
    private MediaPlayer mediaPlayer = null;
    private String path = "";
    private String audio_url = "";
    private int playposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startBtn = (Button) findViewById(R.id.startBtn);
        pauseBtn =  (Button) findViewById(R.id.pauseBtn);
        restartBtn = (Button) findViewById(R.id.restartBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        path = Environment.getExternalStorageDirectory().getAbsolutePath();  // "sdcard"
        audio_url = path+"/Music/papa_lastresort.mp3";

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    File file  = new File(audio_url);
                if(file.exists()){
                    playAudio(audio_url);
                    Toast.makeText(getApplicationContext(), "음악 시작", Toast.LENGTH_SHORT).show();
                }


            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null) {
                    playposition = MainActivity.this.mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(), "음악 중지", Toast.LENGTH_SHORT).show();
                }
            }
        });
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer !=null && !mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playposition);
                Toast.makeText(getApplicationContext(), "음악 재시작", Toast.LENGTH_SHORT).show();
                }
            }
        });


        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopAudio();
                Toast.makeText(getApplicationContext(), "음악 종료", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void playAudio(String url){
        try {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setDataSource(url);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void StopAudio(){
        if(this.mediaPlayer != null){
            this.mediaPlayer.release(); //메모리해제
            //this.mediaPlayer.stop();

        }
    }

}
