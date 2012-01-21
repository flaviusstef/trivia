package com.adaptionsoft.games.trivia;

import java.util.Random;

public class Player {

	private String name;
	private int coins = 0;
	private int location = 0;
	private boolean penalized = false;

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
	
	public void receiveCoin() {
		coins++;
	}
	
	public int coins() {
		return coins;
	}
	
	public int location() {
		return location;
	}
	
	public void moveTo(int location) {
		this.location = location;
	}
	
	public void sendToPenaltyBox() {
		this.penalized = true;
	}

	public void releaseFromPenaltyBox() {
		this.penalized = false;
	}

	public boolean inPenaltyBox() {
		return this.penalized;
	}
}
