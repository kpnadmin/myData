package com.bignerdranch.android.mazerun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MazeRunMainActivity extends AppCompatActivity {


    private TextView mMazeNameLabel;
    private TextView mRemainingGoalsLabel;
    private TextView mStepsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_run_main);




    }
}
