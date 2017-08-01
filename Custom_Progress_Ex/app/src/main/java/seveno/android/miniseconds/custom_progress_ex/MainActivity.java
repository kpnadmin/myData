package seveno.android.miniseconds.custom_progress_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static ProgressBar bar_time = null;
    private static ProgressBar bar_percent = null;
    private static TextView tv1 = null;
    private static TextView tv2 = null;

    private StartUpTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        bar_time = (ProgressBar)findViewById(R.id.bar_time);
        bar_time.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar_time));

        bar_time.setProgress(0);
        bar_percent = (ProgressBar)findViewById(R.id.bar_percent);
        bar_percent.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar_percent));

        bar_percent.setProgress(0);
        task = new StartUpTask(tv1, tv2);
        ProgressBar[] bars = new ProgressBar[2];

        bars[0] = bar_time;
        bars[1] = bar_percent;
        task.execute(bars);


    }
}
