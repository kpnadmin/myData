package arabiannight.tistory.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class TestHandlerActivity extends Activity {
	
	private Handler mHandler;
	private Runnable mRunnable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mRunnable = new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext()
						, ResultPage.class);
				startActivity(intent);
			}
		};
		
		mHandler = new Handler();
		mHandler.postDelayed(mRunnable, 5000);
		
	}
	
	@Override
    protected void onDestroy() {
    	Log.i("test", "onDstory()");
    	mHandler.removeCallbacks(mRunnable);
    	super.onDestroy();
    }
}
