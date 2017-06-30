package com.android.samplelayoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    LinearLayout viewContainer = null;
    LayoutInflater inflater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContainer = (LinearLayout) findViewById(R.id.container);
                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                inflater.inflate(R.layout.layout_sub1, viewContainer, true);

                CheckBox checkBox = (CheckBox)viewContainer.findViewById(R.id.checkBox);
                checkBox.setText("로딩되었습니다.");
            }
        });


    }
}
