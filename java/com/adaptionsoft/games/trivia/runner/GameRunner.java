package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		Game aGame = new Game();
		
		String[] players = {"Chet", "Pat", "Sue"};
		for (String player : players)
			aGame.add(player);
		
		Random rand = new Random();
	
		do {
			aGame.roll(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				aGame.wrongAnswer();
			} else {
				aGame.wasCorrectlyAnswered();
			}
		} while (!aGame.isGameOver());
		
	}
}
