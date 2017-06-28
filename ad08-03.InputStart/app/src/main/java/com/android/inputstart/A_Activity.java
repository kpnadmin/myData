package com.android.inputstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class A_Activity extends AppCompatActivity {

    private Button btn1;
    private EditText edit1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);


        btn1 = (Button) findViewById(R.id.btn1);
        edit1 = (EditText) findViewById(R.id.edit1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), B_Activity.class);
                intent.putExtra("name", edit1.getText().toString());
                startActivity(intent);
            }
        });


    }
}
