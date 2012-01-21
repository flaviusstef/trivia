package com.adaptionsoft.games.trivia;

import java.util.HashSet;
import java.util.Set;

public class Guard {
    Set<Integer> penalizedPlayers = new HashSet<Integer>();
	private int currentPlayer;
	private View view;
    
	public Guard(View v) {
		this.view = v;
	}
	
	public void sendToPenaltyBox(int player) {
		penalizedPlayers.add(player);
	}

	public boolean canPlay(int player) {
		return !penalizedPlayers.contains(player);
	}

	public void playerRolled(int roll) {
		if (!canPlay(currentPlayer)) {
			if (getsOutOfPenaltyBox(roll)) {
				view.gettingOutOfPenaltyBox();
			} else {
				view.notGettingOutOfPenaltyBox();
			}
		}
	}
	
	private boolean getsOutOfPenaltyBox(int roll) {
		return roll %2 != 0;
	}
	
}
