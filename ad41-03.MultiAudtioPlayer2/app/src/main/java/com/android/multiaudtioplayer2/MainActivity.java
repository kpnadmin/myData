package com.android.multiaudtioplayer2;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnPause, btnStop;
    TextView tvMP3, tvTime;
    SeekBar sbMP3;
    ArrayList<String> mp3List;// mp3파일목록
    ArrayAdapter<String> mAdapter;
    String selectedMP3;
    String pathMP3;
    Thread thread;
    // mediaPlayer
    MediaPlayer mPlayer;
    private boolean PAUSED;
    private int playposition = -1;
    private int positionListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPause = (Button) findViewById(R.id.btnPause);

        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        tvTime = (TextView) findViewById(R.id.tvTime);
        sbMP3 = (SeekBar) findViewById(R.id.sbMP3);
        //SD 카드에서 파일목록을 읽어서 확장명 mp3파일만 저장
        mp3List = new ArrayList<String>();// mp3파일만 저장
        pathMP3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music";
        File mydir = new File(pathMP3);
        if (mydir.isDirectory()) {
            File[] files = mydir.listFiles();

            // Music 폴더에서 이외의 파일이 있는지를 대비
            for (File file : files) {
                String fileName = file.getName();
                String extName = fileName.substring(fileName.length() - 3);
                if (extName.equals("mp3")) {
                    mp3List.add(fileName);
                }

            }
        }
        // ListView 와 mp3List를 연결
        listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(mAdapter);
        listViewMP3.setItemChecked(0, true); // 앱시작시 가장 위의 노래를 자동으로 플레이 하기 위해
        selectedMP3 = mp3List.get(0);
        //listview 핸들러 작성
        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = mAdapter.getItem(position);
                listViewMP3.setItemChecked(position, true);
                positionListView = position;
                if (mPlayer != null) {
                    mPlayer.stop();
                    // mPlayer.release();
                    mPlayer.reset();
                }
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(pathMP3 + "/" + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                sbMP3.setProgress(0);
                sbMP3.setVisibility(View.VISIBLE);
                tvMP3.setText("실행 음악 : " + selectedMP3);
            }
        });


        sbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mPlayer.seekTo(sbMP3.getProgress());
            }
        });
        // 일시정지 중지버튼 감추기
        btnPause.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                Start();
                break;
            case R.id.btnPause:
                Pause();
                break;
            case R.id.btnStop:
                Stop();
                break;
        }


    }

    private void Start() {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(pathMP3 + "/" + selectedMP3);
            mPlayer.prepare();
            mPlayer.start();
            Progressupdate();
            btnPlay.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.VISIBLE);
            sbMP3.setVisibility(View.VISIBLE);

            tvMP3.setText("실행음악 : " + selectedMP3); // 실행중인 음악 출력
            // 실행중인 음악 시간 출력
            Progressupdate();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(positionListView < mAdapter.getCount()){
                        listViewMP3.setItemChecked(++positionListView, true);
                      selectedMP3 = mAdapter.getItem(positionListView);
                        Start();
                    }
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void Progressupdate() {
        final Handler mHandler = new Handler();
        thread = new Thread() {
            @Override
            public void run() {
                if (mPlayer == null) return;
                sbMP3.setMax(mPlayer.getDuration());
                while (mPlayer.isPlaying()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            sbMP3.setProgress(mPlayer.getCurrentPosition());
                            //진행시간변경
                            SimpleDateFormat tf = new SimpleDateFormat("mm:ss");
                            String formatted = tf.format(mPlayer.getCurrentPosition());
                            tvTime.setText("진행시간 : " + formatted);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    private void Stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.reset();
        }

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);
        sbMP3.setVisibility(View.INVISIBLE);
        sbMP3.setProgress(0);
        // 실행중인 음악 출력
        tvMP3.setText("실행중인 음악 : ");
        // 실행중인 음악 시간 출력
        tvTime.setText("진행시간 : ");
        //
        PAUSED = false;
        thread.interrupt();
    }

    private void Pause() {
        if (mPlayer !=null && mPlayer.isPlaying()) {
            mPlayer.pause();
            PAUSED = false;
           /* try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            btnPause.setText("이어듣기");
            playposition = MainActivity.this.mPlayer.getCurrentPosition();
        }else if(mPlayer !=null && !mPlayer.isPlaying()){
            mPlayer.seekTo(playposition);
            mPlayer.start();
            PAUSED = true;
            btnPause.setText("일시정지");
            Progressupdate();
        }


    }


}
