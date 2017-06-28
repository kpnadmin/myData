package com.android.scrollview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddButtonActivity extends AppCompatActivity {

    private LinearLayout layout1;
    private Button addButton1;
    private Button addButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_button);

        addButton1 = (Button) findViewById(R.id.addButton1);
        addButton5 = (Button) findViewById(R.id.addButton5);
            //
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10,10,10,10);
                //
                Button btn = new Button(getApplicationContext());
                btn.setText("Push Me");
                btn.setLayoutParams(lp);
                btn.setBackgroundColor(Color.CYAN);
                layout1.addView(btn);
                //




            }
        });


    }
}
