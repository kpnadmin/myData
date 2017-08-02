package seveno.android.miniseconds.threadhandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mainValue = 0;
    int backValue = 0 ;
    TextView mainText;
    TextView backText;
    Handler handler = new Handler(); // 메인에서 생성한 핸들러


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.mainvalue);
        backText = (TextView) findViewById(R.id.backvalue);

        BackThread thread = new BackThread();
        thread.setDaemon(true);
        thread.start();

    }
    public void mOnClick(View v){
        mainValue++;
        mainText.setText("MainValue"+mainValue);
    }
    class BackThread extends Thread{
        @Override
        public void run() {
            while(true){
                backValue++;
                // 메인스레드에 있던 handler겍체를 사용하여
                // Runnable 객체를 보내고 (post)
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        backText.setText("BackValue: " + backValue);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
