package seveno.android.miniseconds.SpeedNumGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import seveno.android.miniseconds.R;

public class FinishScreen extends AppCompatActivity {
    private static final int ERROR_PENALTY_SECONDS = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);

        long initialTime = getIntent().getLongExtra("seveno.android.miniseconds.speednumgame.initialTime",0);
        int numErrors = getIntent().getIntExtra("seveno.android.miniseconds.speednumgame.numErrors",0);
        setupInitialTimeTextView(initialTime);
        setupFinalTimeTextView(initialTime, numErrors);


    }


    private String convertToMinutesAndSeconds(long toConvert){
        int seconds = (int) (toConvert / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;
        String minutesAndSeconds = String.format(Locale.ENGLISH,"%d:%02d", minutes, seconds);
        return minutesAndSeconds;
    }
    private void setupInitialTimeTextView(long initialTime){
        TextView initialTimeTextView = (TextView)findViewById(getResources().getIdentifier("textview_time","id",this.getPackageName()));
        String initialTimeString = convertToMinutesAndSeconds(initialTime);
        initialTimeTextView.setText("Your time was "+initialTimeString);
    }

    private void setupNumErrorsTextView(int numErrors){
        TextView errorTextView = (TextView)findViewById(getResources().getIdentifier("textview_errors","id",this.getPackageName()));
        int timePenalty = numErrors*ERROR_PENALTY_SECONDS;
        if(numErrors==1){
            errorTextView.setText("You made "+numErrors+" error for a time penalty of "+timePenalty+" seconds.");
        } else {
            errorTextView.setText("You made "+numErrors+" errors for a time penalty of "+timePenalty+" seconds.");
        }
    }

    private void setupFinalTimeTextView(long initialTime, int numErrors){
        TextView finalTimeTextView = (TextView)findViewById(getResources().getIdentifier("textview_finaltime","id",this.getPackageName()));
        String finalTime = convertToMinutesAndSeconds(initialTime + (numErrors*ERROR_PENALTY_SECONDS*1000));
        finalTimeTextView.setText("Your final time is "+finalTime);
    }

    public void btn_ReturnMain(View view){

        finish();
    }
}