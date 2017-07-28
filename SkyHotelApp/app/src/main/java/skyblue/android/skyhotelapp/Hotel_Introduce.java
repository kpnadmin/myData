package skyblue.android.skyhotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Hotel_Introduce extends AppCompatActivity {
    private ImageButton prev_btn_intro;

    private static int RES_CODE = 0;
    private final int RES_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__introduce);

        prev_btn_intro = (ImageButton) findViewById(R.id.prev_btn_intro);

        prev_btn_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
               /* int no1 = intent.getIntExtra("var1", 0) ;
                int no2 = intent.getIntExtra("var2", 0);
                int sum = no1 +no2;
                intent.putExtra("sum", sum);*/
                setResult(RES_OK, intent);
                finish();
            }
        });

    }
}
