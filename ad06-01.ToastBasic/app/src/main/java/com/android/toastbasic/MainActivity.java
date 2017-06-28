package com.android.toastbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Toast toast;
    private int count = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

               if(toast != null)  toast.cancel();
            toast =  Toast.makeText(getApplicationContext(), "hello"+count, Toast.LENGTH_SHORT);
                toast.setText("hello"+count);
            toast.show();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null)  toast.cancel();
                toast = Toast.makeText(getApplicationContext(), "hello"+count, Toast.LENGTH_SHORT);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                int xOffset = (int)(Math.random() * dm.widthPixels);

                int yOffset = (int)(Math.random() * dm.heightPixels);
                count++;
            toast.setGravity(Gravity.TOP | Gravity.LEFT,xOffset ,yOffset );
                toast.setText("hello"+count);

                toast.show();
            }
        });

    }
}
