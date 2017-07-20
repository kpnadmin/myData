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
* About: This class is for setting up the Finish Screen which appears after a game ends.
* It sets up 3 TextViews: One showing the initial time, one the number of errors made, and one the final time (after error penalty).
* It also has an additional optional TextView, which is shown to display how the time ranks as a high score (if it is one), 
* and OK button which when pressed returns the user to the Main Menu.
*/

package game.speed.android.speed_number_game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class FinishScreen extends Activity {
	private static final int ERROR_PENALTY_SECONDS = 5;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_screen);
		long initialTime = getIntent().getLongExtra("game.speed.android.speed_number_game.initialTime",0);
		int numErrors = getIntent().getIntExtra("game.speed.android.speed_number_game.numErrors",0);
		int position = getIntent().getIntExtra("game.speed.android.speed_number_game.position",0);
		setupInitialTimeTextView(initialTime);
		setupNumErrorsTextView(numErrors);
		setupFinalTimeTextView(initialTime, numErrors);	
		setupHighScorePositionTextView(position);
	}

	private void setupInitialTimeTextView(long initialTime){	
		TextView initialTimeTextView = (TextView)findViewById(getResources().getIdentifier("textview_time","id",this.getPackageName()));
		String initialTimeString = convertToMinutesAndSeconds(initialTime);
		initialTimeTextView.setText("Your time was "+initialTimeString);
	}
	
	private void setupNumErrorsTextView(int numErrors){
		TextView errorTextView = (TextView)findViewById(getResources().getIdentifier("textview_errors","id",this.getPackageName()));
		int timePenalty = numErrors*ERROR_PENALTY_SECONDS;
		if(numErrors==1){
			errorTextView.setText("You made "+numErrors+" error for a time penalty of "+timePenalty+" seconds.");
		} else {
			errorTextView.setText("You made "+numErrors+" errors for a time penalty of "+timePenalty+" seconds.");
		}
	}
	
	private void setupFinalTimeTextView(long initialTime, int numErrors){
		TextView finalTimeTextView = (TextView)findViewById(getResources().getIdentifier("textview_finaltime","id",this.getPackageName()));
		String finalTime = convertToMinutesAndSeconds(initialTime + (numErrors*ERROR_PENALTY_SECONDS*1000));
		finalTimeTextView.setText("Your final time is "+finalTime);
	}
	
	private void setupHighScorePositionTextView(int position){
		TextView positionTextView = (TextView)findViewById(getResources().getIdentifier("textview_position","id",this.getPackageName()));
		if(position==0||position>10){
			positionTextView.setVisibility(View.GONE);
		} else {
			if(position==1) {
				positionTextView.setText("This is your highest score.");
			} else if(position==2){
				positionTextView.setText("This is your 2nd highest score.");
			} else if(position==3){
				positionTextView.setText("This is your 3rd highest score.");
			} else if(position>=4 && position<=10){
				positionTextView.setText("This is your "+position+"th highest score.");
			}
		}
	}
	
    private String convertToMinutesAndSeconds(long toConvert){
    	int seconds = (int) (toConvert / 1000);
    	int minutes = seconds / 60;
    	seconds     = seconds % 60;
    	String minutesAndSeconds = String.format(Locale.ENGLISH,"%d:%02d", minutes, seconds);
    	return minutesAndSeconds;
    }
	
	public void startOk(View view){
		finish();
	}
}
