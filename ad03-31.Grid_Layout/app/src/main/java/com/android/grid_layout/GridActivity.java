package com.android.grid_layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class GridActivity extends AppCompatActivity {

    EditText edit1, edit2, edit_funciton;
    Button btnAdd, btnSub,btnMul,btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        setTitle("초간단 계산기");

       edit1 = (EditText) findViewById(R.id.edit1);
    }
}
