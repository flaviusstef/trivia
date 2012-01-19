package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.View;


public class GameRunner {

	public static void main(String[] args) {
		View v = new View();
		Game game = new Game(v);
		
		String[] players = {"Chet", "Pat", "Sue"};
		for (String player : players)
			game.add(player);
		
		do {
			game.nextRound();
		} while (!game.isGameOver());
		
	}
}
