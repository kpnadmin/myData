package seveno.android.miniseconds.AvoidStarGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import seveno.android.miniseconds.MainActivity;
import seveno.android.miniseconds.R;

public class FinishScreenA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen_a);
    }


    public void btn_ReturnMainGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.initialTime",0);
        intent.putExtra("seveno.android.miniseconds.avoidstargame.numErrors",0);
        startActivityForResult(intent, 0);
        finish();
    }
}
