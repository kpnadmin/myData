package seveno.android.miniseconds.tapp_downex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;

import java.io.*;

public class HighScoreActivity extends Activity {

    int[] topScores;
    String[] topNames;
    TextView scoreOne, scoreTwo, scoreThree, scoreFour,scoreFive,
             scoreSix, scoreSeven, scoreEight, scoreNine, scoreTen;
    int scoreCount;
    int nameCount;
    Animation fade, fade2, fade3, fade4, fade5, fade6, fade7, fade8, fade9, fade10, bouncy;
    ImageView logo, place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        overridePendingTransition(0,0);


        bouncy = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.floating);
        fade = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein);
        fade2 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein2);
        fade3 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein3);
        fade4 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein4);
        fade5 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein5);
        fade6 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein6);
        fade7 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein7);
        fade8 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein8);
        fade9 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein9);
        fade10 = AnimationUtils.loadAnimation(HighScoreActivity.this, R.anim.fadein10);

        final Button fifteen = (Button)findViewById(R.id.fts);
        final Button thirty = (Button)findViewById(R.id.ts);
        final Button fourtyfive = (Button)findViewById(R.id.ffs);

        logo = (ImageView)findViewById(R.id.bouncyLogo);
        place = (ImageView)findViewById(R.id.noPlace);

        place.setVisibility(View.VISIBLE);

        scoreOne = (TextView) findViewById(R.id.hScore);
        scoreTwo = (TextView) findViewById(R.id.hScore2);
        scoreThree = (TextView) findViewById(R.id.hScore3);
        scoreFour = (TextView) findViewById(R.id.hScore4);
        scoreFive = (TextView) findViewById(R.id.hScore5);
        scoreSix = (TextView) findViewById(R.id.hScore6);
        scoreSeven = (TextView) findViewById(R.id.hScore7);
        scoreEight = (TextView) findViewById(R.id.hScore8);
        scoreNine = (TextView) findViewById(R.id.hScore9);
        scoreTen = (TextView) findViewById(R.id.hScore10);

        logo.startAnimation(bouncy);

        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                view2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                place.setVisibility(View.INVISIBLE);
                readfile(15);

            }
        });

        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                view2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                place.setVisibility(View.INVISIBLE);
                readfile(30);
            }
        });

        fourtyfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                view2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                place.setVisibility(View.INVISIBLE);
                readfile(45);
            }
        });
    }

    public void readfile(int time){

        topNames  = new String [10];
        topScores  = new int[10];

        scoreOne.setText("");
        scoreTwo.setText("");
        scoreThree.setText("");
        scoreFour.setText("");
        scoreFive.setText("");
        scoreSix.setText("");
        scoreSeven.setText("");
        scoreEight.setText("");
        scoreNine.setText("");
        scoreTen.setText("");

        File nameFile = new File (this.getFilesDir() + "/", "name" + time + ".txt");
        File scoreFile = new File (this.getFilesDir() + "/", "score" + time + ".txt");

        try{
            if (!nameFile.exists()) {
                nameFile.createNewFile();
            }
            if (!scoreFile.exists()) {
                scoreFile.createNewFile();
            }

            BufferedReader score_reader = new BufferedReader(new FileReader (scoreFile));
            BufferedReader name_reader = new BufferedReader(new FileReader (nameFile));

            String scoreLine;
            String nameLine;

            scoreCount = 0;
            nameCount = 0;

            while((scoreLine = score_reader.readLine()) != null){
                topScores[scoreCount] = Integer.parseInt(scoreLine);
                scoreCount++;
            }
            score_reader.close();

            while((nameLine = name_reader.readLine()) != null){
                topNames[nameCount] = nameLine;
                nameCount++;
            }
            name_reader.close();

            ////SHOW STUFF NOW

            if (nameCount != 0){

                if (topScores[0] != 0){
                    scoreOne.setText("1. " + topNames[0] + "     " + topScores[0]);
                }
                if (topScores[1] != 0){
                    scoreTwo.setText("2. " + topNames[1] + "     " + topScores[1]);
                }
                if (topScores[2] != 0){
                    scoreThree.setText("3. " + topNames[2] + "     " + topScores[2]);
                }
                if (topScores[3] != 0){
                    scoreFour.setText("4. " + topNames[3] + "     " + topScores[3]);
                }
                if (topScores[4] != 0){
                    scoreFive.setText("5. " + topNames[4] + "     " + topScores[4]);
                }
                if (topScores[5] != 0){
                    scoreSix.setText("6. " + topNames[5] + "     " + topScores[5]);
                }
                if (topScores[6] != 0){
                    scoreSeven.setText("7. " + topNames[6] + "     " + topScores[6]);
                }
                if (topScores[7] != 0){
                    scoreEight.setText("8. " + topNames[7] + "     " + topScores[7]);
                }
                if (topScores[8] != 0){
                    scoreNine.setText("9. " + topNames[8] + "     " + topScores[8]);
                }
                if (topScores[9] != 0){
                    scoreTen.setText("10. " + topNames[9] + "     " + topScores[9]);
                }

                //ANIMATIONS
                scoreOne.startAnimation(fade);
                scoreTwo.startAnimation(fade2);
                scoreThree.startAnimation(fade3);
                scoreFour.startAnimation(fade4);
                scoreFive.startAnimation(fade5);
                scoreSix.startAnimation(fade6);
                scoreSeven.startAnimation(fade7);
                scoreEight.startAnimation(fade8);
                scoreNine.startAnimation(fade9);
                scoreTen.startAnimation(fade10);
                //END ANIMATIONS

            } else {
                scoreFive.setText("Hm, looks like there are no scores for");
                scoreSix.setText(time + " seconds. Try playing a new game!");
            }


            /////

        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Error Writing Score.", Toast.LENGTH_SHORT);
            toast.show();
        } catch(IOException e){
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Error Writing Score.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main_menu = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(main_menu, 0);
        finish();
    }
}
