package com.android.threadhandlerex02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText EditText;
    TextView Count_TextView;
    Button Button;
    int inputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText = (EditText) findViewById(R.id.EditText);
        Count_TextView = (TextView) findViewById(R.id.Count_TextView);
        Button = (Button) findViewById(R.id.Button);
    }
    public void Button_Click(View v){
        String input = EditText.getText().toString();
        Count_TextView.setText(input);

        if(input.equals("")){
            Toast.makeText(this, "일치", Toast.LENGTH_SHORT).show();
        }else{
            inputNumber = Integer.parseInt(input);

            if(inputNumber==0){
                Toast.makeText(this, "불일치", Toast.LENGTH_SHORT).show();
                return;
            }
            Button.setEnabled(false);

            final Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    /**
                     * �Ѱܹ��� what���� �̿��� ������ �۾��� �з��մϴ�
                     */
                    if(msg.what==1){
                        Log.d("What Number : ", "What is 1");
                    }else if(msg.what==2){
                        Log.d("What Number : ", "What is 2");
                    }

                    Count_TextView.setText(""+inputNumber);
                    if(inputNumber==0){
                        Toast.makeText(MainActivity.this, "카운트넘버", Toast.LENGTH_SHORT).show();
                        Button.setEnabled(true);
                    }
                }
            };
            Runnable task = new Runnable(){
                public void run(){
                    while(inputNumber > 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}

                        --inputNumber;
                        /**
                         * sendEmptyMessage�� �ܼ��� int�� What�� �����ϱ� ������
                         * Message��ü�� ������ �ʿ䰡 �����ϴ�
                         */
                        handler.sendEmptyMessage(1);

                        /**
                         * sendMessage�� message��ü�� �Ѱ��ָ�,
                         * �̶� what�� ��, arg1, arg2�� int�� ���� �ټ��� �ְ�
                         * intent���� ��ü ��ü�� �ѱ���� �ִ� (message.obj = ��ü)
                         */
                        Message message= Message.obtain();
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
