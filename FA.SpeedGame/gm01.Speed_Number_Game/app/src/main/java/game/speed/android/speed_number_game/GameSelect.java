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
* About: This class handles the 'Game Select' screen.
* It uses a Spinner for game type selection, which changes what starting sequence number is shown in the TextView below it.
*/

package game.speed.android.speed_number_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class GameSelect extends Activity implements OnItemSelectedListener {
	private int currentGameType = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_select);
		Spinner gameTypeSpinner = (Spinner)findViewById(R.id.spinner_game_type);
		gameTypeSpinner.setAdapter(ArrayAdapter.createFromResource(this, R.array.game_choices, R.layout.spinner_layout));
		gameTypeSpinner.setOnItemSelectedListener(this);
	}
	
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    	TextView startingNumberTextView = (TextView)findViewById(R.id.textview_starting_number);
    	String startingNumberString = "Your starting number is ";
    	currentGameType = pos;
    	if(pos==0 || pos==10){//Singles or Odds selected, sequence starts with 1.
    		startingNumberTextView.setText(startingNumberString+"1");
    	} else if(pos==1 || pos==11){//Multiples of 2 or primes selected, sequence starts with 2.
    		startingNumberTextView.setText(startingNumberString+"2");
    	} else if(pos>=2 && pos<=10){//Multiples of between 3-10 selected, sequence starts with that multiple.
    		startingNumberTextView.setText(startingNumberString+(pos+1));
    	}
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
    
    public void startGame(View view){
    	Intent intent = new Intent(this, Play.class);
    	intent.putExtra("game.speed.android.speed_number_game.currentGameType",currentGameType);
    	startActivity(intent);
    	finish();
    }
}
