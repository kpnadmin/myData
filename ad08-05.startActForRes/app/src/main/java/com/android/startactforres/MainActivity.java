package com.android.startactforres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1, edit2;
    private Button btn1;

    private static int RES_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);

        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SubActivity.class);



                intent.putExtra("var1", Integer.parseInt(String.valueOf(edit1.getText())));
                intent.putExtra("var2",Integer.parseInt(String.valueOf(edit2.getText())));

                startActivityForResult(intent,RES_CODE);




            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Intent intent = new Intent();

      int sum = data.getIntExtra("sum",RES_CODE );


      Toast.makeText(MainActivity.this, "결과 : " + sum, Toast.LENGTH_SHORT).show();
    }




}
