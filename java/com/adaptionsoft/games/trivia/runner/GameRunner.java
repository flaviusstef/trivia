package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.Game;
import com.adaptionsoft.games.trivia.Guard;
import com.adaptionsoft.games.trivia.Player;
import com.adaptionsoft.games.trivia.Scholar;
import com.adaptionsoft.games.trivia.View;


public class GameRunner {

	public static void main(String[] args) {
		View v = new View();
		Scholar sch = new Scholar();
		Guard g = new Guard(v);
		Game game = new Game(v, sch, g);
		
		String[] playerNames = {"Chet", "Pat", "Sue"};
		for (String name : playerNames)
			game.addPlayer(new Player(name));
		
		do {
			game.nextRound();
		} while (!game.isFinished());
		
	}
}
