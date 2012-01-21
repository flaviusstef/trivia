package com.adaptionsoft.games.trivia;

import java.util.HashMap;
import java.util.Map;

public class ScoreKeeper {
    private Map<Integer, Integer> purses2 = new HashMap<Integer, Integer>();

	public void addCoinFor(int player) {
		if (!purses2.containsKey(player)) {
			purses2.put(player, 0);
		}
		int newTotal = purses2.get(player) + 1;
		purses2.put(player, newTotal);
	}

	public int coinsOf(int player) {
		return purses2.get(player);
	}
	
	public boolean hasWinner() {
		return purses2.values().contains(6);
	}

}
