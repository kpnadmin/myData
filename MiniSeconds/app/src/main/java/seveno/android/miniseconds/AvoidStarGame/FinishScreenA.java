package seveno.android.miniseconds.AvoidStarGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import seveno.android.miniseconds.MainActivity;
import seveno.android.miniseconds.R;

public class FinishScreenA extends AppCompatActivity {
    private static final int ERROR_PENALTY_SECONDS = 10;
    private TextView txt_a_finished, txt_a_position, txt_a_errors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen_a);
        txt_a_finished = (TextView) findViewById(R.id.txt_a_finished);
        long initialTime = getIntent().getLongExtra("seveno.android.miniseconds.avoidstargame.initialATime",40000);
        setupInitialTimeTextView(initialTime);
        txt_a_errors = (TextView)findViewById(R.id.txt_a_errors);
        txt_a_position  = (TextView) findViewById(R.id.txt_a_position);

    }
    private String convertToMinutesAndSeconds(long toConvert){
        int seconds = (int) (toConvert / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;
        String minutesAndSeconds = String.format(Locale.ENGLISH,"%d:%02d", minutes, seconds);
        return minutesAndSeconds;
    }
    private void setupInitialTimeTextView(long initialTime){
        TextView initialTimeTextView = (TextView)findViewById(getResources().getIdentifier("txt_a_time","id",this.getPackageName()));
        String initialTimeString = convertToMinutesAndSeconds(initialTime);
        initialTimeTextView.setText("Your time was "+initialTimeString);
    }


    public void btn_ReturnMainGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.initialTime",0);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.numErrors",0);
        startActivityForResult(intent, 0);
        finish();
    }
}
