package com.android.grid_calc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class GridActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRe;
    TextView CalcResult;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {
            R.id.BtnNum0,
            R.id.BtnNum1,
            R.id.BtnNum2,
            R.id.BtnNum3,
            R.id.BtnNum4,
            R.id.BtnNum5,
            R.id.BtnNum6,
            R.id.BtnNum7,
            R.id.BtnNum8,
            R.id.BtnNum9
    };
    int i ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        setTitle("Grid 계산기");

        edit1 = (EditText) findViewById(R.id.Edit1);
        //edit1.requestFocus();
        edit2 = (EditText) findViewById(R.id.Edit2);
        //edit2.requestFocus();
        //im.hideSoftInputFromWindow(edit2.getWindowToken(), 0);
        //
        btnAdd = (Button) findViewById(R.id.Btn_Add);
        btnSub = (Button) findViewById(R.id.Btn_Sub);
        btnMul = (Button) findViewById(R.id.Btn_Mul);
        btnDiv = (Button) findViewById(R.id.Btn_div);
        btnRe = (Button) findViewById(R.id.Btn_reminder);


        final Map<String, String> calc = new HashMap<String, String>();
        calc.put("reset", "N");



        CalcResult = (TextView) findViewById(R.id.CalcResult);


        edit1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edit1.requestFocus();
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(edit1.getWindowToken(), 0);
                return true;
            }
        });
        edit2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edit2.requestFocus();
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(edit2.getWindowToken(), 0);
                return true;
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                if(num1.getBytes().length <= 0){//빈값이 넘어올때의 처리

                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(num2.getBytes().length <=0){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    CalcResult.setText(result.toString());

                }
                edit1.setText("");//내용물비워주기
                edit2.setText("");
            }
        });
    //
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.getBytes().length <= 0){//빈값이 넘어올때의 처리

                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(num2.getBytes().length <=0){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {

                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    CalcResult.setText(result.toString());
                }
                edit1.setText("");//내용물비워주기
                edit2.setText("");
            }
        });
                //
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.getBytes().length <= 0){//빈값이 넘어올때의 처리

                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(num2.getBytes().length <=0){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    CalcResult.setText(result.toString());
                }
                edit1.setText("");//내용물비워주기
                edit2.setText("");
            }
        });

            //
        btnDiv.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();

            if (num1.isEmpty() || num2.isEmpty()) {
                Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
            } else if( num2.equals("0") ) {
                Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다", Toast.LENGTH_LONG).show();
            } else {
                result = Integer.parseInt(num1) * Integer.parseInt(num2);

                CalcResult.setText(result.toString());

                edit1.setText("");//내용물비워주기
                edit2.setText("");
            }



        }
    });

        btnRe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.getBytes().length <= 0){//빈값이 넘어올때의 처리

                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(num2.getBytes().length <=0){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) % Integer.parseInt(num2);
                    CalcResult.setText(result.toString());
                }
                edit1.setText("");//내용물비워주기
                edit2.setText("");
            }
        });



        for(i = 0 ;  i<numBtnIDs.length; i++){
            numButtons[i] = (Button)findViewById(numBtnIDs[i]);

        }
        //
        for(i = 0 ;i<numBtnIDs.length; i++){
            final int index;
            index = i ;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edit1.isFocused() == true){
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }else if(edit2.isFocused() == true){
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }//for

    }

}
