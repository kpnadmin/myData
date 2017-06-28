package com.android.contextmenu;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button btn1 = null;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배경색바꾸기");
        btn1 = (Button) findViewById(R.id.btn1);
        // 위젯과 context 연결
        layout = (LinearLayout) findViewById(R.id.layout);

        registerForContextMenu(btn1);




    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        MenuInflater inflator = getMenuInflater();

        if(v.getId() == R.id.btn1){
            inflator.inflate(R.menu.menu1, menu);
        }

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);

                switch(item.getItemId()){
                    case R.id.itemRed:
                        layout.setBackgroundColor(Color.RED);
                        break;
                    case R.id.itemGreen:
                        layout.setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.itemBlue:
                        layout.setBackgroundColor(Color.BLUE);
                        break;
                }

        return true;
    }

}
