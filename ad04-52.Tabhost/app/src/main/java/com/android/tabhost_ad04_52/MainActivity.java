package com.android.tabhost_ad04_52;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost) findViewById(R.id.tabhost);

        host.setup();

        TabHost.TabSpec tab1 = null;


        tab1 = host.newTabSpec("TAB ONE");
        tab1.setIndicator("강아지");
        tab1.setContent(R.id.tab1);
        host.addTab(tab1);

        tab1 = host.newTabSpec("TAB TWO");
        tab1.setIndicator("고양이");
        tab1.setContent(R.id.tab2);
        host.addTab(tab1);

        tab1 = host.newTabSpec("TAB THREE");
        tab1.setIndicator("토끼");
        tab1.setContent(R.id.tab3);
        host.addTab(tab1);

        tab1 = host.newTabSpec("TAB FOUR");
        tab1.setIndicator("말");
        tab1.setContent(R.id.tab4);
        host.addTab(tab1);

        //
        /*host.getTabWidget().getChildAt(0).getLayoutParams().height-80;
        */

    }
}
