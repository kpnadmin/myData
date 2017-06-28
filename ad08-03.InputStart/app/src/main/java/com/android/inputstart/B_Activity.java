package com.android.inputstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class B_Activity extends AppCompatActivity {

    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        txt1 = (TextView) findViewById(R.id.txt1);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        txt1.setText(name);
    }
}
