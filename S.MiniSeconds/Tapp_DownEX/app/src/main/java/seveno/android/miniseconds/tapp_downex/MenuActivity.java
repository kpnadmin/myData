package seveno.android.miniseconds.tapp_downex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.*;
import android.content.Intent;
import android.view.animation.AnimationUtils;

public class MenuActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        overridePendingTransition(0,0);

        final Animation fade = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadein);
        final Animation fade2 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadein2);
        final Animation fade3 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadein3);
        final Animation fadeout = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadeout);
        final Animation fadeout2 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadeout2);
        final Animation fadeout3 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.fadeout3);
        final Animation bouncy = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.floating);
        final ImageView logo = (ImageView)findViewById(R.id.bouncyLogo);
        final Button play = (Button)findViewById(R.id.button);
        final Button instr = (Button)findViewById(R.id.button2);
        final Button score = (Button)findViewById(R.id.button3);

        //final MediaPlayer buttonSound = MediaPlayer.create(MenuActivity.this, R.raw.click);

        logo.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
        instr.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);

        //ANIMATIONS
        logo.startAnimation(fade);
        play.startAnimation(fade);
        instr.startAnimation(fade2);
        score.startAnimation(fade3);
        logo.startAnimation(bouncy);
        //END ANIMATIONS

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play.startAnimation(fadeout3);
                instr.startAnimation(fadeout2);
                score.startAnimation(fadeout);

                play.setVisibility(View.INVISIBLE);
                instr.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);

                try {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent launch_prep = new Intent(MenuActivity.this, TimeSelectActivity.class);
                            startActivityForResult(launch_prep,0);
                            finish();
                        }
                    }, 1000);
                } catch(Exception e){e.printStackTrace();}
            }
        });

                instr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle ("How To Play");
                builder.setMessage("The objective of Tapp Down is to tap the screen as many times as you can in a limited time frame.")
                        .setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                play.startAnimation(fadeout3);
                instr.startAnimation(fadeout2);
                score.startAnimation(fadeout);

                play.setVisibility(View.INVISIBLE);
                instr.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);

                try {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent launch_score = new Intent(MenuActivity.this, HighScoreActivity.class);
                            startActivityForResult(launch_score, 0);
                            finish();
                        }
                    }, 1000);

                } catch(Exception e){e.printStackTrace();}
               }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alert_box = new AlertDialog.Builder(MenuActivity.this);
            alert_box.setTitle("Are you sure you want to leave?");
            alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });

            alert_box.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alert_box.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {


        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
