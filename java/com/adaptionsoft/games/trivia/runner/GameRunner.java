package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.Game;
import com.adaptionsoft.games.trivia.Player;
import com.adaptionsoft.games.trivia.QuizMaker;
import com.adaptionsoft.games.trivia.View;

public class GameRunner {

	public static void main(String[] args) {
		View v = new View();
		QuizMaker quizMaker = new QuizMaker(v);
		Game game = new Game(v, quizMaker);

		String[] playerNames = { "Chet", "Pat", "Sue" };
		for (String name : playerNames)
			game.addPlayer(new Player(name));

		do {
			game.nextRound();
		} while (!game.isFinished());

	}
}
