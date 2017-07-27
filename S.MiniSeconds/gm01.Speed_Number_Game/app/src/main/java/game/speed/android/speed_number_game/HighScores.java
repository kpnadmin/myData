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
* About: This class is for setting up the High Scores screen.
* It uses a ScrollView containing a TableLayout to display up to 10 high scores, where only TableRows containing a high score are shown.
* It has a Button which when pressed deletes all high scores (after a confirmation AlertDialog).
* If there are no high scores, a TextView is shown instead of the ScrollView and Button stating this.
*/

package game.speed.android.speed_number_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

public class HighScores extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		SharedPreferences prefs = this.getSharedPreferences("game.speed.android.speed_number_game", Context.MODE_PRIVATE);
		ArrayList<String> highScoreModes = getHighScoreModes(prefs);
		ArrayList<Integer> highScoreErrors = getHighScoreErrors(prefs);
		ArrayList<String> highScoreFinalTimes = getHighScoreFinalTimes(prefs);
		int numHighScores = highScoreFinalTimes.size();
		if(numHighScores>0){
			displayHighScores(numHighScores, highScoreModes, highScoreErrors, highScoreFinalTimes);
		} else {
			displayNoHighScores();
		}
	}
	
	private ArrayList<String> getHighScoreModes(SharedPreferences prefs){
		ArrayList<String> highScoreModes = new ArrayList<String>(10);
		for(int i = 1; i <= 10; i++){
			String currentMode = prefs.getString("highScoreMode"+i, null);
			if(currentMode!=null){
				highScoreModes.add(currentMode);
			}
		}
		highScoreModes.trimToSize();
		return highScoreModes;
	}
	
	private ArrayList<Integer> getHighScoreErrors(SharedPreferences prefs){
		ArrayList<Integer> highScoreErrors = new ArrayList<Integer>(10);
		for(int i = 1; i <= 10; i++){
			int currentErrors = prefs.getInt("highScoreErrors"+i, -1);
			if(currentErrors!=-1){
				highScoreErrors.add(currentErrors);
			}
		}
		highScoreErrors.trimToSize();
		return highScoreErrors;
	}
	
	private ArrayList<String> getHighScoreFinalTimes(SharedPreferences prefs){
		ArrayList<String> highScoreFinalTimes = new ArrayList<String>(10);
		for(int i = 1; i <= 10; i++){
			String currentTime = prefs.getString("highScoreStringTime"+i, null);
			if(currentTime!=null){
				highScoreFinalTimes.add(currentTime);
			}
		}
		highScoreFinalTimes.trimToSize();
		return highScoreFinalTimes;
	}
	
	private void displayHighScores(int numHighScores, ArrayList<String> highScoreModes, ArrayList<Integer> highScoreErrors, ArrayList<String> highScoreFinalTimes){		
		ScrollView highScoresScrollView = (ScrollView)findViewById(R.id.scrollview_high_scores);
		Button resetHighScoresButton = (Button)findViewById(R.id.button_reset_high_scores);
		TextView noHighScoresTextView = (TextView)findViewById(R.id.textview_no_high_scores);
		highScoresScrollView.setVisibility(View.VISIBLE);
		resetHighScoresButton.setVisibility(View.VISIBLE);
		noHighScoresTextView.setVisibility(View.GONE);
		for(int i = numHighScores+1; i <= 10; i++){//Hide the TableRows that won't contain High Scores.
			TableRow currentRow = (TableRow)findViewById(getResources().getIdentifier("tablerow_highscores_"+i,"id",this.getPackageName()));
			currentRow.setVisibility(View.GONE);
		}
		for(int i = 1; i <= numHighScores; i++){//Add the High Scores to their TextViews.
			TableRow currentRow = (TableRow)findViewById(getResources().getIdentifier("tablerow_highscores_"+i,"id",this.getPackageName()));
			currentRow.setVisibility(View.VISIBLE);
			String textViewId = "textview_table_";
			if(i<=10){
				TextView currentPositionTextView = (TextView)findViewById(getResources().getIdentifier(textViewId+i+"_0", "id", getPackageName()));
				currentPositionTextView.setText(""+i);
				TextView currentModeTextView = (TextView)findViewById(getResources().getIdentifier(textViewId+i+"_1", "id", getPackageName()));
				currentModeTextView.setText(highScoreModes.remove(0));
				TextView currentErrorTextView = (TextView)findViewById(getResources().getIdentifier(textViewId+i+"_2", "id", getPackageName()));
				currentErrorTextView.setText(""+highScoreErrors.remove(0));
				TextView currentTimeTextView = (TextView)findViewById(getResources().getIdentifier(textViewId+i+"_3", "id", getPackageName()));
				currentTimeTextView.setText(highScoreFinalTimes.remove(0));
			}
		}
	}
	
	private void displayNoHighScores(){
		ScrollView highScoresScrollView = (ScrollView)findViewById(R.id.scrollview_high_scores);
		Button resetHighScoresButton = (Button)findViewById(R.id.button_reset_high_scores);
		TextView noHighScoresTextView = (TextView)findViewById(R.id.textview_no_high_scores);
		highScoresScrollView.setVisibility(View.GONE);
		resetHighScoresButton.setVisibility(View.GONE);
		noHighScoresTextView.setVisibility(View.VISIBLE);
		noHighScoresTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, (float) 0.8));
		noHighScoresTextView.setText("There are no high scores.");
	}
	
	//When the delete high scores button is pressed, this shows an AlertDialog to confirm it.
	public void confirmHighScoreDeletion(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Delete all high scores?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				deleteHighScores();
			}});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}});
		AlertDialog dialog = builder.create();		
		dialog.show();
		TextView dialogTextView = (TextView)dialog.findViewById(android.R.id.message);
		dialogTextView.setGravity(Gravity.CENTER);
	}
	
	public void deleteHighScores(){
		SharedPreferences prefs = this.getSharedPreferences("game.speed.android.speed_number_game", Context.MODE_PRIVATE);
		prefs.edit().clear().commit();
		displayNoHighScores();
	}
}
