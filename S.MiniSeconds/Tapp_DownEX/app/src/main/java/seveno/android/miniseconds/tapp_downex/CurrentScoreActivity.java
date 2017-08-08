package seveno.android.miniseconds.tapp_downex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import android.widget.EditText;

public class CurrentScoreActivity extends Activity {

    int intScore;
    int origTime;
    String curr_score = "";
    String curr_name = "";
    int[] topScores = new int[10];
    String[] topNames = new String[]{curr_name, curr_name, curr_name, curr_name, curr_name,
                                curr_name, curr_name, curr_name, curr_name, curr_name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_score);
        overridePendingTransition(R.anim.in,R.anim.out);


        final Animation bouncy = AnimationUtils.loadAnimation(CurrentScoreActivity.this, R.anim.floating);
        final ImageView logo = (ImageView)findViewById(R.id.yourScore);

        logo.startAnimation(bouncy);

        Intent show_score = getIntent();
        intScore = show_score.getIntExtra("myScore", 0);
        origTime = show_score.getIntExtra("origTime", 0);

        final EditText playerName = (EditText)findViewById(R.id.myName);
        final TextView roundScore = (TextView) findViewById(R.id.end_score);
        roundScore.setText("" + intScore);

        //INITIALIZE BUTTONS
        Button home = (Button)findViewById(R.id.menu);
        Button replay = (Button)findViewById(R.id.replay_game);

        //SCORE & NAME FOR THIS ROUND
        curr_score = ("" + intScore);
        readfile(origTime);

       //RETURN TO MENU
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                curr_name = playerName.getText().toString();

                if (curr_name.length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "You forgot to enter your name!", 250);
                    toast.show();
                } else if (curr_name.length() > 15){
                    Toast toast = Toast.makeText(getApplicationContext(), "Whoa, that name's too long!", 250);
                    toast.show();
                } else {
                    writefile(origTime);

                    Intent launch_game = new Intent(view.getContext(), MenuActivity.class);
                    startActivityForResult(launch_game,0);
                    finish();
                }
            }
        });

       //REPLAY GAME
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                curr_name = playerName.getText().toString();

                if (curr_name.length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "You forgot to enter your name!", 250);
                    toast.show();
                } else if (curr_name.length() > 15){
                    Toast toast = Toast.makeText(getApplicationContext(), "Whoa, that name's too long!", 250);
                    toast.show();
                } else {
                    writefile(origTime);

                    Intent launch_game = new Intent(view.getContext(), TimeSelectActivity.class);
                    startActivityForResult(launch_game,0);
                    finish();
                }
            }
        });
    }

    public void readfile(int time){

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

            int scoreCount = 0;
            int nameCount = 0;

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

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writefile(int time){

        for (int i =0; i < 10; i++){
            if (intScore > topScores[i]){
                for (int j = 9; j > i; j--){
                    topScores[j] =  topScores [j-1];
                    topNames[j] = topNames[j-1];
                }
                topScores[i] = intScore;
                topNames[i] = curr_name;
                break;
            }
        }

        String temp;

        File nameFile = new File (this.getFilesDir() + "/", "name" + time + ".txt");
        File scoreFile = new File (this.getFilesDir() + "/", "score" + time + ".txt");

        try{
            if (!nameFile.exists()) {
                nameFile.createNewFile();
            }
            if (!scoreFile.exists()) {
                scoreFile.createNewFile();
            }

            BufferedWriter n = new BufferedWriter(new FileWriter (nameFile.getAbsoluteFile(), false));
            BufferedWriter s = new BufferedWriter(new FileWriter (scoreFile.getAbsoluteFile(), false));

            for (int i =0; i<10; i++){
                n.write (topNames[i]);
                n.newLine();
            }

            n.close();

            for (int i = 0; i<10; i++){
                temp = (topScores[i] + "");
                s.write (temp);
                s.newLine();
            }

            s.close();

        }catch (FileNotFoundException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Error Writing Score.", Toast.LENGTH_SHORT);
            toast.show();
        }catch (IOException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Error Writing Score.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main_menu = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(main_menu, 0);
        finish();
    }
}
