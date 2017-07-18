package com.android.multiaudioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listviewMP3;
    Button btnPlay, btnStop, btnPause;
    TextView tvMP3;
    SeekBar sbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = "";
    String audio_url = "";
    MediaPlayer mPlayer;
    private int playposition = -1;
    Boolean stop_switch = false;
    private ProgressUpdate progressUpdate;
    boolean isPlaying = true;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 MP3 플레이어");

        mp3List = new ArrayList<String>();


        mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath();  // "sdcard"
        mp3Path = mp3Path+"/Music/";

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length() -3);
            if(extName.equals((String)"mp3")) mp3List.add(fileName);
            }
            listviewMP3 = (ListView)findViewById(R.id.listViewMP3);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,mp3List);
            listviewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listviewMP3.setAdapter(adapter);
            listviewMP3.setItemChecked(0,true);

            listviewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedMP3 = mp3List.get(position);
                }
            });
            selectedMP3 = mp3List.get(0);

    btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        sbMP3 = (SeekBar) findViewById(R.id.sbMP3);
        btnPause = (Button) findViewById(R.id.btnPause);





        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    sbMP3.setVisibility(View.VISIBLE);
                    mPlayer.prepare();
                    mPlayer.start();
                    sbMP3.setMax(mPlayer.getDuration());
                    progressUpdate = new ProgressUpdate();
                    progressUpdate.start();
                    btnPlay.setClickable(false);
                    btnPlay.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.VISIBLE);
                    btnStop.setClickable(true);
                    btnPause.setClickable(true);
                    tvMP3.setText("실행중인 음악 : " + selectedMP3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                btnPause.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                sbMP3.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer !=null && mPlayer.isPlaying()){
                    playposition = MainActivity.this.mPlayer.getCurrentPosition();
                    mPlayer.pause();
                    btnPause.setText("재시작");
                    Toast.makeText(getApplicationContext(), "음악 일시정지", Toast.LENGTH_SHORT).show();
                    stop_switch = false;
                }else if(mPlayer !=null && !mPlayer.isPlaying()){
                    mPlayer.start();
                    mPlayer.seekTo(playposition);
                    btnPause.setText("일시정지");
                    Toast.makeText(getApplicationContext(), "음악 재시작", Toast.LENGTH_SHORT).show();
                    stop_switch = true;
                }
            }
        });


    }


    class ProgressUpdate extends Thread{
        @Override
        public void run() {
            while(isPlaying){
                try {
                    Thread.sleep(500);
                    if(mPlayer!=null){
                        sbMP3.setProgress(mPlayer.getCurrentPosition());
                    }
                } catch (Exception e) {
                    Log.e("ProgressUpdate",e.getMessage());
                }

            }
        }
    }



    }

