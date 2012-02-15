package com.adaptionsoft.games.trivia.runner;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private boolean notAWinner;
	private int seed;

	public GameRunner(int seed) {
		this.seed = seed;
	}

	public static void main(String[] args) throws FileNotFoundException {
		for (int seed=0; seed<10000; seed++) {
			System.setOut(new PrintStream("masters/" + seed + ".txt"));
			new GameRunner(seed).run();
		}
		
	}

	public void run() {
		Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");
		
		Random rand = new Random(seed);
	
		do {
			aGame.roll(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
