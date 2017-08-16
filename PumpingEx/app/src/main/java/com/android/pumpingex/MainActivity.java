package com.android.pumpingex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);


        EventSurfaceView mySurfaceView = new EventSurfaceView(this);
        setContentView(mySurfaceView);



    }
}
