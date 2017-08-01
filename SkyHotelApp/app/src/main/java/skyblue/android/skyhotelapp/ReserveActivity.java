package skyblue.android.skyhotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ReserveActivity extends AppCompatActivity {

    private ImageButton prev_btn_res;
    private final int RES_OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        prev_btn_res = (ImageButton) findViewById(R.id.prev_btn_res);
        prev_btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(RES_OK, intent);
                finish();
            }
        });


    }
}
