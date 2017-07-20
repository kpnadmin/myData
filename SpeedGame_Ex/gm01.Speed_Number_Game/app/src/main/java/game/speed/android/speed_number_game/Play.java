/* 
* Speedy Numbers Android Game
* Copyright 2013 Jesse Toms
* 
*   This file is part of Speedy Numbers.
*
*   Speedy Numbers is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   Speedy Numbers is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with Speedy Numbers.  If not, see <http://www.gnu.org/licenses/>.
*   
* About: This class handles the 'Play' screen.
* It has an action bar at the top of the screen displaying a timer and number of errors the user has made. 
* It has 50 buttons which display the number sequence on the screen. When a button has been correctly pressed, it becomes unpressable 
* and becomes translucent. After all buttons have been correctly pressed, it will determine if the final time was good enough to be a 
* high score and update the saved high scores accordingly.
*/

package game.speed.android.speed_number_game;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Locale;

public class Play extends Activity {
	private static final int ERROR_PENALTY_SECONDS = 5;
	private static Sequence sequence;
	private static long startTime;
	private static long timeTakenMillis;
	private static boolean timerRunning;
	private static TextView timerTextView;
	private static TextView errorsMadeTextView;
	private static int numErrors = 0;
	private static Handler h2 = new Handler();
   	private static Runnable run;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		if(savedInstanceState==null){//On first startup, creates the sequence, begins the timer and does some cleanup work.
			sequence = new Sequence(getIntent().getIntExtra("game.speed.android.speed_number_game.currentGameType", 0));
			startTime = System.currentTimeMillis();
			numErrors = 0;
			timerRunning = true;
			run = new Runnable() {
			    @Override
			    public void run() {
			    	timeTakenMillis = System.currentTimeMillis() - startTime;
			    	timerTextView.setText("Time: "+(convertToMinutesAndSeconds(timeTakenMillis)));
			    	h2.postDelayed(this, 500);
			        }
			    };
		}
		setupActionBar();
		addSequenceToButtons();
		if(timerRunning){
			h2.postDelayed(run, 0);
		} else {
			timerTextView.setText(convertToMinutesAndSeconds(timeTakenMillis));
		}
	}

	//On button click, check if it is correct (If not then increase error count). If correct then make the button unpressable and translucent, 
	//then check if all buttons have been correctly pressed. If they have then update the saved high scores if necessary. Start the finish screen.
	public void buttonClick(View v) throws InterruptedException{
		if(sequence.isCorrect(Integer.parseInt((String)v.getTag()))){
			((Button)v).setAlpha((float)0.2);
			((Button)v).setClickable(false);
			if(sequence.allCorrect()){
				h2.removeCallbacks(run);
				timerRunning = false;
				long finalTime = timeTakenMillis + (numErrors*ERROR_PENALTY_SECONDS*1000);
				int highScorePosition = findHighScorePosition(finalTime);
				if(highScorePosition >= 1 && highScorePosition <= 10){
					updateHighScores(highScorePosition, finalTime);
				}
		    	Intent intent = new Intent(this, FinishScreen.class);
		    	intent.putExtra("game.speed.android.speed_number_game.initialTime",timeTakenMillis);
		    	intent.putExtra("game.speed.android.speed_number_game.numErrors",numErrors);
		    	intent.putExtra("game.speed.android.speed_number_game.position",highScorePosition);
		    	startActivity(intent);
		    	finish();
			}
		} else {
			numErrors++;
			errorsMadeTextView.setText("Errors: "+numErrors);
		}
	}
	
    private void setupActionBar(){
		ActionBar actionBar = getActionBar();
        errorsMadeTextView = new TextView(this);
        errorsMadeTextView.setTextColor(Color.BLACK);
        errorsMadeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
        errorsMadeTextView.setText("Errors: "+numErrors);
		LayoutParams errorsMadeTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
		errorsMadeTextViewParams.weight = 1;
		errorsMadeTextViewParams.gravity = Gravity.CENTER;
		timerTextView = new TextView(this);
		timerTextView.setTextColor(Color.BLACK);
		timerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
		LayoutParams timerTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
		timerTextViewParams.weight = 1;
		timerTextViewParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
		LinearLayout actionBarLayout = new LinearLayout(this);
		actionBarLayout.addView(errorsMadeTextView,errorsMadeTextViewParams);
		actionBarLayout.addView(timerTextView,timerTextViewParams);
		ActionBar.LayoutParams actionBarLayoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		actionBarLayout.setLayoutParams(actionBarLayoutParams);
		actionBar.setCustomView(actionBarLayout);
		actionBar.setDisplayShowCustomEnabled(true);
    }
    
    private void addSequenceToButtons(){
		for(int i = 1; i <= 50; i++){
			String buttonName = "button_"+i;
			Button currentButton = (Button)findViewById(getResources().getIdentifier(buttonName,"id",this.getPackageName()));
			int buttonSequenceNumber = sequence.getIntegerAt(i-1);
			currentButton.setText(""+buttonSequenceNumber);
			currentButton.setTag(""+buttonSequenceNumber);
			if(sequence.isDone(sequence.getIntegerAt(i-1))){
				currentButton.setAlpha((float)0.2);
				currentButton.setClickable(false);
			}
		}
    }
    
	public int findHighScorePosition(long finalTime){
		int position = 0;
    	SharedPreferences prefs = this.getSharedPreferences("com.gmail.jesseotoms.speedynumbers", Context.MODE_PRIVATE);   	
		long currentHighScoreTime = prefs.getLong("highScoreLongTime"+10, 0);
    	if(finalTime<currentHighScoreTime || currentHighScoreTime==0){
    		position = 10;
    		for(int i = 9; i > 0; i--){
    			currentHighScoreTime = prefs.getLong("highScoreLongTime"+i, 0);
    			if(finalTime<currentHighScoreTime || currentHighScoreTime==0){
    				position = i;
    			}
    		}
    	}
		return position;
	}
	
	public void updateHighScores(int pos, long finalTimeToAdd){
		String longTimeKey = "highScoreLongTime";
    	String modeKey = "highScoreMode";
    	String errorKey = "highScoreErrors";
    	String stringTimeKey = "highScoreStringTime";
    	SharedPreferences prefs = this.getSharedPreferences("com.gmail.jesseotoms.speedynumbers", Context.MODE_PRIVATE);   
    	if(pos == 10){
    		prefs.edit().putLong(longTimeKey+10, finalTimeToAdd).commit();
    		prefs.edit().putString(modeKey+10, sequence.getgameType()).commit();
    		prefs.edit().putInt(errorKey+10, numErrors).commit();
    		prefs.edit().putString(stringTimeKey+10, convertToMinutesAndSeconds(finalTimeToAdd)).commit();
    	} else {
	    	for(int i = 9; i>=pos; i--){//Move all high scores starting at position down one position.
	    		long currentLongTime = prefs.getLong(longTimeKey+i, 0);
	    		String currentMode = prefs.getString(modeKey+i, null);
	    		int currentErrors = prefs.getInt(errorKey+i, -1);
	    		String currentStringTime = prefs.getString(stringTimeKey+i, null);
	    		if(currentMode!=null){
	    			prefs.edit().putLong(longTimeKey+(i+1), currentLongTime).commit();
		    		prefs.edit().putString(modeKey+(i+1), currentMode).commit();
		    		prefs.edit().putInt(errorKey+(i+1), currentErrors).commit();
		    		prefs.edit().putString(stringTimeKey+(i+1), currentStringTime).commit();
	    		}
	    	}
	    	prefs.edit().putLong(longTimeKey+pos, finalTimeToAdd).commit();
    		prefs.edit().putString(modeKey+pos, sequence.getgameType()).commit();
    		prefs.edit().putInt(errorKey+pos, numErrors).commit();
    		prefs.edit().putString(stringTimeKey+pos, convertToMinutesAndSeconds(finalTimeToAdd)).commit();
    	}
	}
	
    public String convertToMinutesAndSeconds(long toConvert){
    	int seconds = (int) (toConvert / 1000);
    	int minutes = seconds / 60;
    	seconds     = seconds % 60;
    	String minutesAndSeconds = String.format(Locale.ENGLISH,"%d:%02d", minutes, seconds);
    	return minutesAndSeconds;
    }
}
