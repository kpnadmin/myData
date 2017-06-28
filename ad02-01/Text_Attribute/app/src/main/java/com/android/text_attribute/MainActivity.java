package com.android.text_attribute;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = null;
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new ButtonClick());

    }

    // 3. 내부 클래스를 이용한 리스너 설정 선언 찾기 설정
    class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(), "내부 클래스 핸들러 방식", Toast.LENGTH_SHORT).show();
            setTitle("텍스트속성변경후");
            TextView textSize = (TextView) findViewById(R.id.textSize);
            TextView textColor = (TextView) findViewById(R.id.textColor);
            TextView textStyle = (TextView) findViewById(R.id.textStyle);
            TextView typeface = (TextView) findViewById(R.id.typeFace);
            TextView singleLine = (TextView) findViewById(R.id.singleLine);
            TextView text_practice1 = (TextView) findViewById(R.id.text_practice1);
            TextView text_practice2 = (TextView) findViewById(R.id.text_practice2);
            TextView text_practice3 = (TextView) findViewById(R.id.text_practice3);

            textSize.setTextSize(20);

            String strColor = "#0000FF";
            textColor.setTextColor(Color.parseColor(strColor));

            textStyle.setTypeface(Typeface.SERIF,Typeface.ITALIC);

            text_practice1.setText("안녕하세요");
            text_practice2.setTextSize(20);
            text_practice2.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);

            text_practice3.setSingleLine();
            text_practice3.setText("가나다라마바사아자차카타파하");



        }
    }

}
