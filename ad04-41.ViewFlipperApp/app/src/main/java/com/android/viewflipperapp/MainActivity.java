package com.android.viewflipperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    private Button btnStart, btnStop, btnGo, btnBack;
    private ViewFlipper viewFlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                  // btnGo = (Button) findViewById(R.id.btnGo);
                  //  btnBack = (Button) findViewById(R.id.btnBack);
                viewFlip  = (ViewFlipper) findViewById(R.id.viewFlip);
                  //  btnStart = (Button) findViewById(R.id.btnStart);
                 //   btnStop = (Button) findViewById(R.id.btnStop);


        


      /*  btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      viewFlip.showNext();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                               viewFlip.showPrevious();
            }
        });
*/
/*
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       viewFlip.startFlipping();
                viewFlip.setFlipInterval(1000);

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                             viewFlip.stopFlipping();

            }
        });
*/
    }
    public void onClicked(View view){
        switch(view.getId()){
            case R.id.btnBack:
                viewFlip.showPrevious();
                break;
            case R.id.btnGo:
                viewFlip.showNext();
                break;
            case R.id.btnStart:
                viewFlip.setAutoStart(true);
                viewFlip.setFlipInterval(1000);
                viewFlip.startFlipping();
                break;
            case R.id.btnStop:
                viewFlip.stopFlipping();
                break;


        }
    }

}
