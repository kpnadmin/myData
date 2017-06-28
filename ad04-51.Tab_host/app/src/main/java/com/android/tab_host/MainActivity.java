package com.android.tab_host;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml tabHost  가져오기
        TabHost host = (TabHost) findViewById(android.R.id.tabhost);
        //
        TabHost.TabSpec tab1 = null;
      /*  TabHost.TabSpec tab2 = null;
        TabHost.TabSpec tab3 = null;*/

      /*
      * findViewById() 함수를 통해 TabHost를 설정하는 경우, TabHost의 참조를 가져온 다음 반드시 setup() 함수를 호출해야 합니다.

      *
      * */

        //
        host.setup();
        tab1 = host.newTabSpec("TAB ONE");
        tab1.setIndicator("TAB ONE");
        tab1.setContent(R.id.tab1);
        host.addTab(tab1);

       //
        tab1 = host.newTabSpec("TAB TWO");
        tab1.setContent(R.id.tab2);
        tab1.setIndicator("TAB TWO");
        host.addTab(tab1);
        //
        tab1 = host.newTabSpec("TAB THREE");
        tab1.setContent(R.id.tab3);
        tab1.setIndicator("TAB THREE");
        host.addTab(tab1);


    }
}
