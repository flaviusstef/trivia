package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.Game;
import com.adaptionsoft.games.trivia.Guard;
import com.adaptionsoft.games.trivia.Scholar;
import com.adaptionsoft.games.trivia.ScoreKeeper;
import com.adaptionsoft.games.trivia.View;


public class GameRunner {

	public static void main(String[] args) {
		View v = new View();
		ScoreKeeper s = new ScoreKeeper();
		Scholar sch = new Scholar();
		Guard g = new Guard(v);
		Game game = new Game(v, s, sch, g);
		
		String[] players = {"Chet", "Pat", "Sue"};
		for (String player : players)
			game.addPlayer(player);
		
		do {
			game.nextRound();
		} while (!game.isFinished());
		
	}
}
