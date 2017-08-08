package seveno.android.miniseconds.tapp_downex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class TimeSelectActivity extends Activity{

    int gameTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sel);
        overridePendingTransition(0,0);

        final Button sec15 = (Button)findViewById(R.id.button);
        final Button sec30 = (Button)findViewById(R.id.button2);
        final Button sec45 = (Button)findViewById(R.id.button3);
        final ImageView logo = (ImageView)findViewById(R.id.bouncyLogo);

        final Animation fade = AnimationUtils.loadAnimation(TimeSelectActivity.this, R.anim.fadein);
        final Animation fade2 = AnimationUtils.loadAnimation(TimeSelectActivity.this, R.anim.fadein2);
        final Animation fade3 = AnimationUtils.loadAnimation(TimeSelectActivity.this, R.anim.fadein3);
        final Animation bouncy = AnimationUtils.loadAnimation(TimeSelectActivity.this, R.anim.floating);

                logo.startAnimation(bouncy);

                sec15.startAnimation(fade);

                sec30.startAnimation(fade2);

                sec45.startAnimation(fade3);

        sec15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                gameTime = 15000;
                Intent launch_game = new Intent(TimeSelectActivity.this, MainActivity.class);
                launch_game.putExtra("gameTime", gameTime);
                startActivity(launch_game);
                finish();
            }
        });

        sec30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                gameTime = 30000;
                Intent launch_game = new Intent(TimeSelectActivity.this, MainActivity.class);
                launch_game.putExtra("gameTime", gameTime);
                startActivity(launch_game);
                finish();
            }
        });

        sec45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                gameTime = 45000;
                Intent launch_game = new Intent(TimeSelectActivity.this, MainActivity.class);
                launch_game.putExtra("gameTime", gameTime);
                startActivity(launch_game);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main_menu = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(main_menu, 0);
        finish();
    }
}
