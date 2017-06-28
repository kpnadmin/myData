package com.android.contextmenu2;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button btn1 = null;
    private ConstraintLayout constlayout = null;
    private EditText editText1 = null;
    private ImageButton imgbtn = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("배경색바꾸기");
        btn1 = (Button) findViewById(R.id.btn1);
        editText1  = (EditText) findViewById(R.id.editText1);
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);



        // 위젯과 context 연결
        registerForContextMenu(btn1);
        registerForContextMenu(editText1);
        registerForContextMenu(imgbtn);


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {



        MenuInflater inflator = getMenuInflater();

        if(v.getId() == R.id.btn1){
            inflator.inflate(R.menu.menu1, menu);
            menu.setHeaderIcon(R.drawable.stara);
            menu.setHeaderTitle("Button Menu");
        }else if(v.getId() == R.id.editText1){
            inflator.inflate(R.menu.menu2, menu);
        }else if(v.getId() == R.id.imgbtn){
            inflator.inflate(R.menu.menu3, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);


    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        ConstraintLayout constlayout = (ConstraintLayout) findViewById(R.id.constlayout);

        switch(item.getItemId()){
            case R.id.itemRed:
                constlayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen:
                constlayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue:
                constlayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.write1:
                break;
            case R.id.writeconfig:
                break;
            case R.id.choosePicture:
                break;

        }

        return true;
    }





}
