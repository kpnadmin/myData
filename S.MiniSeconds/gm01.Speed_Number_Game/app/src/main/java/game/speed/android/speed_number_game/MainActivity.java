package game.speed.android.speed_number_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void startQuickplay(View view){
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("game.speed.android.speed_number_game.currentGameType",0);
        startActivity(intent);
    }

    public void startPlay(View view){
        Intent intent = new Intent(this, GameSelect.class);
        startActivity(intent);
    }

    public void startHighScores(View view){
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
    }

    public void startHelp(View view){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}
