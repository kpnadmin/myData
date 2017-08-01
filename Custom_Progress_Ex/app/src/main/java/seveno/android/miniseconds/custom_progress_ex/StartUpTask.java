package seveno.android.miniseconds.custom_progress_ex;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 김태훈 on 2017-08-02.
 */

public class StartUpTask extends AsyncTask< Integer//excute()실행시 넘겨줄 데이터타입
        , Integer//진행정보 데이터 타입 publishProgress(), onProgressUpdate()의 인수
        , Long//doInBackground() 종료시 리턴될 데이터 타입 onPostExecute()의 인수
        >{
    ProgressBar bar1 = null;
    ProgressBar bar2 = null;
    TextView tv1 = null;
    TextView tv2 = null;
    public StartUpTask(TextView... tv) {
        this.tv1 = tv[0];
        this.tv2 = tv[1];
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(Long result) {
        Log.d("시작","start");
        super.onPostExecute(result);
    }

    protected void onPreExecute() {
        Log.d("시작전","start1");
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        bar1.setProgress(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected Long doInBackground(Integer... params) {
        long numberOfParams = params[0];

        for (int i = 0; i < numberOfParams; i++) {
            SystemClock.sleep(1000);

            publishProgress((int) (((i + 1) / (float) numberOfParams) * 100));
        }
        return numberOfParams;
    }
}

