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
* About: This class is used by Play.class and stores the current sequence, both ordered and randomized.
* The randomedNumbers ArrayList is the order in which numbers are placed on buttons, e.g. button 1 will have the first number in randomedNumbers.
* This class also stores the number of buttons which have been correctly pressed as well as the game type (in an int format).
*/

package game.speed.android.speed_number_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sequence {
	private final ArrayList<Integer> primeNumbers = new ArrayList<Integer>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229));	
	private ArrayList<Integer> randomedNumbers;
	private ArrayList<Integer> orderedNumbers;
	private int numCorrect;
	private int gameType;
	
	//Starts a new sequence determined by the game type.
	public Sequence(int gameType){
		this.gameType = gameType;
		numCorrect = 0;
		orderedNumbers = new ArrayList<Integer>(50);
		if(gameType==0){//Game Type: Singles
			for(int i = 1; i<=50; i++){
				orderedNumbers.add(i);
			}
		} else if(gameType>0 && gameType<10){//Game Type: Multiples
			for(int i = 1; i <= 50; i++){
				orderedNumbers.add(i*(gameType+1));
			}
		} else if(gameType==10){//Game Type: Odds
			for(int i = 0; i < 50; i++){
				orderedNumbers.add(1+(i*2));
			}
		} else if(gameType==11){//Game Type: Primes
			orderedNumbers.addAll(primeNumbers);
		}
		orderedNumbers.trimToSize();
		randomedNumbers = new ArrayList<Integer>(orderedNumbers);
		shuffle();
	}
	
	//Randomizes the sequence in randomedNumbers.
	private void shuffle(){
		Random random = new Random();
		int n = randomedNumbers.size();
		while(n>1){
			int k = random.nextInt(n);
			n--;
			int temp = randomedNumbers.get(n);
			randomedNumbers.set(n,randomedNumbers.get(k));
			randomedNumbers.set(k, temp);
		}
	}
	
	//Returns a number from randomedNumbers to display on a button.
	public int getIntegerAt(int position){
		return randomedNumbers.get(position);
	}

	//Returns the string form of the game type.
	public String getgameType(){
		String modeName = "";
		switch(gameType){
		case 0: modeName = "Singles";
			break;
		case 1: modeName = "Multiples of 2";
			break;
		case 2: modeName = "Multiples of 3";
			break;
		case 3: modeName = "Multiples of 4";
			break;
		case 4: modeName = "Multiples of 5";
			break;
		case 5: modeName = "Multiples of 6";
			break;
		case 6: modeName = "Multiples of 7";
			break;
		case 7: modeName = "Multiples of 8";
			break;
		case 8: modeName = "Multiples of 9";
			break;
		case 9: modeName = "Multiples of 10";
			break;
		case 10: modeName = "Odds";
			break;
		case 11: modeName = "Primes";
			break;
		}
		return modeName;
	}
	
	//Checks if a number has already been completed. Is used to determine which buttons to make unpressable if Play.java has its OnCreate recalled.
	public boolean isDone(int toCheck){
		boolean isDone = false;
		int index = orderedNumbers.indexOf(toCheck);
		if(index>=0 && index<numCorrect){
			isDone=true;
		}
		return isDone;
	}
	
	//Called when a button is pressed on the Play screen, checks if it is the next correct number in the sequence.
	public boolean isCorrect(int toCheck){
		boolean isCorrect = false;
		if(toCheck==orderedNumbers.get(numCorrect)){
			isCorrect = true;
			numCorrect++;
		}
		return isCorrect;
	}
	
	//Checks if all of the buttons have been pressed correctly. Is called each time a new button is found to be correct in Play.class.
	public boolean allCorrect(){
		boolean allCorrect = false;
		if(numCorrect==orderedNumbers.size()){
			allCorrect = true;
		}
		return allCorrect;
	}
}
