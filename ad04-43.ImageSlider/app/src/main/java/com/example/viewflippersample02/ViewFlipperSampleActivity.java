package com.example.viewflippersample02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class ViewFlipperSampleActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private AnimationListener mAnimationListener;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mContext = this;
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
        mViewFlipper.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                return true;
            }
        });


        findViewById(R.id.play).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //sets auto flipping
                mViewFlipper.setAutoStart(true);
                mViewFlipper.setFlipInterval(4000);
                mViewFlipper.startFlipping();
            }
        });

        findViewById(R.id.stop).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //stop auto flipping
                mViewFlipper.stopFlipping();
            }
        });

        findViewById(R.id.swipe_left).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //stop auto flipping
                mViewFlipper.showPrevious();
            }
        });


        findViewById(R.id.swipe_right).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //stop auto flipping
                mViewFlipper.showNext();
            }
        });

        //animation listener
        mAnimationListener = new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                //animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //TODO animation stopped event
            }
        };
    }

}


