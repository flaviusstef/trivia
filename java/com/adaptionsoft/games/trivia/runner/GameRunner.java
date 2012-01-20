package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.Game;
import com.adaptionsoft.games.trivia.Scholar;
import com.adaptionsoft.games.trivia.ScoreKeeper;
import com.adaptionsoft.games.trivia.View;


public class GameRunner {

	public static void main(String[] args) {
		View v = new View();
		ScoreKeeper s = new ScoreKeeper();
		Scholar sch = new Scholar();
		Game game = new Game(v, s, sch);
		
		String[] players = {"Chet", "Pat", "Sue"};
		for (String player : players)
			game.add(player);
		
		do {
			game.nextRound();
		} while (!game.isGameOver());
		
	}
}
