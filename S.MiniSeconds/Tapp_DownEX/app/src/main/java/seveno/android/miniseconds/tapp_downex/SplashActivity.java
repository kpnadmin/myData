package seveno.android.miniseconds.tapp_downex;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    public final int SPLASH_TIME = 990;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

        final Animation fade = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadeout3);
        final ImageView logo = (ImageView)findViewById(R.id.fade_title);
        logo.setVisibility(View.VISIBLE);
        logo.startAnimation(fade);

        try {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    logo.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }, SPLASH_TIME);
        } catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
