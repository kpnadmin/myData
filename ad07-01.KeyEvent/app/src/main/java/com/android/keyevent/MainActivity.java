package com.android.keyevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 위젯 선언
    private Button btn_finish;
    private EditText edt_text;
    private TextView txt_text;

    private Toast toast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯 찾기
        btn_finish = (Button) findViewById(R.id.btn_finish);
        edt_text   = (EditText) findViewById(R.id.edt_text);
        txt_text   = (TextView) findViewById(R.id.txt_text);

        // 핸들러 설정
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edt_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( toast == null) {
                    toast = Toast.makeText(getApplicationContext(), edt_text.getText(), Toast.LENGTH_SHORT);
                }
                //toast.cancel();
                toast.setText(edt_text.getText());
                toast.show();

                txt_text.setText( edt_text.getText() );

                return false;
            }
        });
    }
}
