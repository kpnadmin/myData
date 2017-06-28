package com.android.scrollview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class AutoIncrementScrollViewActivity extends AppCompatActivity {

    private String LOG_TAG = "AddScrollView";

    private LinearLayout layout2 = null;
    private ScrollView scrollView;
    private int count = 0 ;
    private int ht = 0;



    private Button AddBtn_1;
    private Button AddBtn_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_increment_scroll_view);

        scrollView = (ScrollView) findViewById(R.id.scrollview1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        AddBtn_1 = (Button) findViewById(R.id.AddBtn_1);
        AddBtn_5  = (Button) findViewById(R.id.AddBtn_5);


        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);


        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView
                int scrollX = scrollView.getScrollX(); // For HorizontalScrollView
                int bottom = scrollView.getBottom();
                int top = scrollView.getTop();
                int height = scrollView.getHeight();

                // DO SOMETHING WITH THE SCROLL COORDINATES

                int diff = 0 ;
                Log.d("", String.format("x:%d, y:%d, b%d, h:%d, diff:%d",scrollX,scrollY,bottom,height, diff));

                View view = scrollView.getChildAt(scrollView.getChildCount() - 1);

                diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                Log.d("", String.format("x:%d, y:%d, b%d, h:%d, diff:%d",scrollX,scrollY,bottom,height, diff));

               /* if (diff == 0 ) { // 스크롤 bottom

                    Button btn = new Button( getApplicationContext() );
                    btn.setText("button"+count);
                    btn.setLayoutParams(lp);
                    btn.setBackgroundColor(Color.CYAN);
                    layout.addView(btn);

                    scrollView.smoothScrollTo(scrollX, scrollY);
                    scrollToEnd();
                    count ++;
                }*/

                if(layout2.getHeight() == scrollView.getHeight() + scrollView.getScrollY()){
                    addWidget();
                }



            }
        });






        AddBtn_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button btn = new Button( getApplicationContext() );
                btn.setText("Button"+count);
                btn.setLayoutParams(lp);
                btn.setBackgroundColor(Color.CYAN);
                layout2.addView(btn);
                count ++;

            }
        });
        AddBtn_5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (int i = 0 ; i < 5 ; i++) {
                    Button btn = new Button(getApplicationContext());
                    btn.setText("Button"+count);
                    btn.setLayoutParams(lp);
                    layout2.addView(btn);
                    count ++;
                }
            }
        });




    }


    public void scrollToEnd(){
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }

        });

    }


    private void addWidget(){
        for (int i = 1; i <=5; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );
            params.setMargins(10, 10, 10, 10);

            Button btn = new Button(getApplicationContext());
            btn.setText("button" + count++ );
            btn.setBackgroundColor(Color.LTGRAY);
            btn.setLayoutParams(params);
            layout2.addView(btn);

        }
    }



}

       /* for (int i = 2; i < 64; i++) {

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            Button buttonView = new Button(this);
            buttonView.setText("Button " + i);
            layout.addView(buttonView, p);
        }
*/