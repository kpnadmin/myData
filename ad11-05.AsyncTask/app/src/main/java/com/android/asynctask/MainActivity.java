package com.android.asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int      mCount = 0;
    TextView mCountTextView = null;
    Handler  mHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountTextView = (TextView) findViewById(R.id.textView1);
        new CounterTask().execute(20);
    }

    class CounterTask extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... params) {

           int count = 0;
            for(int i = 0 ; i < params[0]; i++){
                SystemClock.sleep(1000); // 1초 멈춤
                publishProgress(count++, i);
            }

            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mCountTextView.setText(String.valueOf(values[0]));
            Log.d("onProgressUpdate","count : " + values[0] + " , i : " + values[1]);
        }

        public CounterTask() {
            super();
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }
    }
}