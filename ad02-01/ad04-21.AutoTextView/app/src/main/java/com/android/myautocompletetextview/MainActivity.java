package com.android.myautocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    private String[] items = {"newyork", "LA", "miami", "friends", "Lost", "Fringe"};

    // 위젯 선언
    private AutoCompleteTextView auto = null ;
    private MultiAutoCompleteTextView multi = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 찾기
        auto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        multi = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        //adaptor 설정 android.R.layout 사용
        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, items);

        //adaptor와 adaptorView(위젯) 연결하기
        auto.setAdapter(adaptor);
        //
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
                multi.setTokenizer(token);
        multi.setAdapter(adaptor);

    }
}
