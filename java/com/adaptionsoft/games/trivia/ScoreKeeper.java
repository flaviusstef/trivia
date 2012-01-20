package com.adaptionsoft.games.trivia;

public class ScoreKeeper {
    private int[] purses  = {0,0,0,0,0,0};
    private boolean hasWinner = false;

	public void addCoinFor(int player) {
		purses[player]++;
		if (purses[player] == 6) 
			hasWinner = true;
	}

	public int coinsOf(int player) {
		return purses[player];
	}
	
	public boolean hasWinner() {
		return hasWinner;
	}

}
