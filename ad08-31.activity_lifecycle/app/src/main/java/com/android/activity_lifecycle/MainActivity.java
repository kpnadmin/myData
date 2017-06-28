package com.android.activity_lifecycle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("life_cycle","onCreate");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life_cycle","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life_cycle","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life_cycle","onResume");
    }




    // 종료 시
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life_cycle","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life_cycle","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life_cycle","onDestroy");
    }

    // 상태 저장 2개 메소드


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("life_cycle","onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("life_cycle","onSaveInstanceState");
    }

}
