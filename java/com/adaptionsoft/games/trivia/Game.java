package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final int LOCATION_COUNT = 12;
	ArrayList<String> playerNames = new ArrayList<String>();
    int[] places = {0,0,0,0,0,0};
    
    int currentPlayer = 0;
	private View view;
	private ScoreKeeper scoreKeeper;
	private Scholar scholar;
	private Guard guard;
    
    public Game(View v, ScoreKeeper sk, Scholar sch, Guard g){
    	this.view = v;
    	this.scoreKeeper = sk;
    	this.scholar = sch;
    	this.guard = g;
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean isFinished() {
		return scoreKeeper.hasWinner();
	}
	
	public void addPlayer(String playerName) {
	    playerNames.add(playerName);
	    view.playerAdded(playerName, howManyPlayers());
	}

	public void nextRound() {
		view.playerName = playerNames.get(currentPlayer);
		int roll = rollDice();
		view.playerRolled(roll);
		guard.playerRolled(roll);
		if (!guard.canPlay(currentPlayer)) {
			selectNextPlayer();
			return;
		}
		moveToNextLocation(roll);
		askQuestion();
		if (!isFinished()) selectNextPlayer();
	}

	private void askQuestion() {
		String question = scholar.generateQuestion(places[currentPlayer]);
		String category = scholar.lastCategory();
		view.askQuestion(category, question);
		verifyAnswer();
	}

	private void verifyAnswer() {
		if (scholar.isAnswerCorrect()) {
			scoreKeeper.addCoinFor(currentPlayer);
			view.correctAnswer(scoreKeeper.coinsOf(currentPlayer));
		} else {
			guard.sendToPenaltyBox(currentPlayer);
			view.incorrectAnswer();
		}
	}

	private void moveToNextLocation(int roll) {
		places[currentPlayer] = (places[currentPlayer] + roll) % LOCATION_COUNT;
		view.movedToLocation(places[currentPlayer]);
	}

	private void selectNextPlayer() {
		currentPlayer++;
		if (currentPlayer == playerNames.size()) currentPlayer = 0;
	}

	private int howManyPlayers() {
		return playerNames.size();
	}
	
	private int rollDice() {
		Random rand = new Random();
		return rand.nextInt(5) + 1;
	}
}