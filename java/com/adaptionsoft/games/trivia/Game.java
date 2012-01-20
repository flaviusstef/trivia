package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private static final int GAME_LOCATIONS = 12;
	ArrayList<String> players = new ArrayList<String>();
    int[] places = {0,0,0,0,0,0};
    Set<Integer> penalizedPlayers = new HashSet<Integer>();
    
    int currentPlayer = 0;
	private View view;
	private ScoreKeeper scoreKeeper;
	private Scholar scholar;
    
    public Game(View v, ScoreKeeper sk, Scholar sch){
    	this.view = v;
    	this.scoreKeeper = sk;
    	this.scholar = sch;
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean isGameOver() {
		return scoreKeeper.hasWinner();
	}

	public void add(String playerName) {
	    players.add(playerName);
	    view.newPlayer(playerName, howManyPlayers());
	}

	public void nextRound() {
		view.playerName = players.get(currentPlayer);
		int roll = rollDice();
		view.playerRolled(roll);
		checkPenaltyBox(roll);
		if (inPenaltyBox(currentPlayer)) {
			selectNextPlayer();
			return;
		}
		moveToNextLocation(roll);
		askQuestion();
		if (!isGameOver()) selectNextPlayer();
	}

	private boolean inPenaltyBox(int player) {
		return penalizedPlayers.contains(player);
	}
	
	private void checkPenaltyBox(int roll) {
		if (inPenaltyBox(currentPlayer)) {
			if (getsOutOfPenaltyBox(roll)) {
				view.gettingOutOfPenaltyBox();
			} else {
				view.notGettingOutOfPenaltyBox();
			}
		}
	}
	
	private boolean getsOutOfPenaltyBox(int roll) {
		return roll %2 != 0;
	}

	private void putInPenaltyBox(int player) {
		penalizedPlayers.add(player);
	}
	
	private void askQuestion() {
		String question = scholar.generateQuestion(places[currentPlayer]);
		String category = scholar.lastCategory();
		view.askQuestion(category, question);
		if (scholar.correctAnswer()) {
			scoreKeeper.addCoinFor(currentPlayer);
			view.correctAnswer(scoreKeeper.coinsOf(currentPlayer));
		} else {
			putInPenaltyBox(currentPlayer);
			view.incorrectAnswer();
		}
	}

	private void moveToNextLocation(int roll) {
		places[currentPlayer] = (places[currentPlayer] + roll) % GAME_LOCATIONS;
		view.moveToLocation(places[currentPlayer]);
	}

	private void selectNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private int howManyPlayers() {
		return players.size();
	}
	
	private int rollDice() {
		Random rand = new Random();
		return rand.nextInt(5) + 1;
	}
}