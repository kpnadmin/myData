package skyblue.android.skyhotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button hotel_info, hotel_customer;
    private Button btn_roomInfo ,btn_reserve ,btn_facilities ,btn_location ,btn_phonecall;
    private static int RES_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotel_info = (Button) findViewById(R.id.hotel_info);
        hotel_customer = (Button) findViewById(R.id.hotel_customer);
        btn_roomInfo = (Button) findViewById(R.id.btn_roomInfo);
        btn_reserve = (Button) findViewById(R.id.btn_reserve);
        btn_facilities = (Button) findViewById(R.id.btn_facilities);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_phonecall = (Button) findViewById(R.id.btn_phonecall);

        btn_location.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_location :
                Intent intent = new Intent(getApplicationContext(),HotelLocActivity.class);

                startActivityForResult(intent,RES_CODE);
        }


    }
}
