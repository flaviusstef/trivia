package com.adaptionsoft.games.trivia;

import java.util.Random;

public class Player {

	private String name;
	private int coins = 0;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public int rollDice() {
		Random rand = new Random();
		return rand.nextInt(5) + 1;
	}
	
	public void addCoin() {
		coins++;
	}
	
	public int coins() {
		return coins;
	}
}
