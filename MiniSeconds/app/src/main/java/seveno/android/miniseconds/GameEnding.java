package seveno.android.miniseconds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEnding extends AppCompatActivity implements View.OnClickListener{
        private TextView text_end_title, text_end_playtime, text_end_Score;
        private Button btn_end_Return, btn_end_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ending);

        text_end_title = (TextView) findViewById(R.id.text_end_title);
        text_end_playtime = (TextView) findViewById(R.id.text_end_playtime);
        text_end_Score = (TextView) findViewById(R.id.text_end_Score);
        btn_end_Return = (Button) findViewById(R.id.btn_end_Return);
        btn_end_List = (Button)findViewById(R.id.btn_end_List);

        btn_end_Return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_end_Return :
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("seveno.android.miniseconds.initialTime",0);
                intent.putExtra("seveno.android.miniseconds.numErrors",0);
                startActivityForResult(intent, 0);
                finish();
                break;
        }
    }
}
