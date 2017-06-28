package com.android.serialization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;

import com.android.serialization.model.ModelParcelable;
import com.android.serialization.model.ModelSerializableData;


public class MainActivity extends AppCompatActivity {

    private int     intData = 0;
    private String  strData = "Superdroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onClick(View view) {
        Uri uri = null;
        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        switch (view.getId()) {
            case R.id.button1: // 기본 타입 데이터 전달
                intent.putExtra("intData", intData );
                intent.putExtra("strData", strData );
                break;
            case R.id.button2:
                ModelSerializableData model = new ModelSerializableData(intData, strData);
                break;
            case R.id.button3:
                
                break;
            case R.id.button4:
                ModelParcelable model4 = new ModelParcelable();
                model4.setIntData(123456789);
                model4.setStrData("Parcelable Object");
                intent.putExtra("PARCELABLE_DATA", model4);
                break;
            case R.id.button5:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("INT_DATA", intData);
                dataBundle.putString("STRING_DATA", strData);
                intent.putExtra("BUNDLE_DATA", dataBundle);
                startActivity(intent);
                break;


        }

        startActivity(intent);
    }
}
