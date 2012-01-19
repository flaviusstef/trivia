package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		Game game = new Game();
		
		String[] players = {"Chet", "Pat", "Sue"};
		for (String player : players)
			game.add(player);
		
		do {
			game.nextRound();
		} while (!game.isGameOver());
		
	}
}
