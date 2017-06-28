package com.android.vert_hor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.Button1)).setOnClickListener(this);;
        button2 = (Button)findViewById(R.id.Button2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.Button1) v.setBackgroundColor(Color.parseColor("#ff0000"));
        else v.setBackgroundColor(Color.parseColor("#0000ff"));
    }
}
