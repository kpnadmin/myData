package seveno.android.miniseconds.progressasyntaskex;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button btn1;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
btn1 = (Button) findViewById(R.id.btn1);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        btn1.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1 :
                new DownloadAsyncTask().execute(100);
                break;
                default:
                    break;
        }
    }
        class DownloadAsyncTask extends AsyncTask<Integer, Integer, Long>{

            @Override
            protected Long doInBackground(Integer... params) {
                long numberOfParams = params[0];

                for (int i = 0; i < numberOfParams; i++) {
                    SystemClock.sleep(1000);

                    publishProgress((int) (((i + 1) / (float) numberOfParams) * 100));
                }
                return numberOfParams;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Long result) {
                btn1.setText("다운로드 시작");
                super.onPostExecute(result);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                pb.setProgress(values[0]);
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled() {
                btn1.setText("취소");
                super.onPreExecute();
            }
        }


}
