package com.android.serialization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import com.android.serialization.model.ModelParcelable;
import com.android.serialization.model.ModelSerializableData;


public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        ModelSerializableData sampleData = (ModelSerializableData) intent.getSerializableExtra("SAMPLE_DATA");


        TextView txt = (TextView) findViewById(R.id.textView);
        ModelSerializableData model3 = (ModelSerializableData) intent.getSerializableExtra("SERIAL_DATA");
        if(model3 != null) txt.setText(model3.toString());

        ModelParcelable model4 = (ModelParcelable) intent.getParcelableExtra("PARCELABLE_DATA");
        if(model4 != null) txt.setText(model4.toString());

        Bundle dataBundle = (Bundle) intent.getBundleExtra("BUNDLE_DATA");
        if(dataBundle != null) txt.setText(dataBundle.getInt("INT_DATA")+"/"+dataBundle.getString("STRING_DATA"));

    }
}
