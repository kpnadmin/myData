package arabiannight.tistory.com;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TestTimerTaskActivity extends Activity {
	
	private TimerTask mTask;
	private Timer mTimer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTask = new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext()
						, ResultPage.class);
				startActivity(intent);
			}
		};
		
		mTimer = new Timer();
		
		mTimer.schedule(mTask, 5000);
//		mTimer.schedule(mTask, 3000, 5000);
    }
    
    @Override
    protected void onDestroy() {
    	Log.i("test", "onDstory()");
    	mTimer.cancel();
    	super.onDestroy();
    }
}