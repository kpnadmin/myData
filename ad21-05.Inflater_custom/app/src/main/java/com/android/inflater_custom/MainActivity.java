package com.android.inflater_custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new_btn = (Button) findViewById(R.id.new_btn);



    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.new_btn:

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text = (TextView) layout.findViewById(R.id.text);
                Toast toast = new Toast(getApplicationContext());
                text.setText("Hello My Android");
                toast.setGravity(Gravity.CENTER, 0 , 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

        }


    }

}
