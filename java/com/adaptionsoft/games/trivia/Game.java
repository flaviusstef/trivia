package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int LOCATION_COUNT = 12;
	private static final int COINS_REQUIRED_TO_WIN = 6;
	List<Player> players = new ArrayList<Player>();
    int[] places = {0,0,0,0,0,0};
    
    int currentPlayer = 0;
	private View view;
	private Scholar scholar;
	private Guard guard;
    
    public Game(View v, Scholar sch, Guard g){
    	this.view = v;
    	this.scholar = sch;
    	this.guard = g;
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean isFinished() {
		return players.get(currentPlayer).coins() == COINS_REQUIRED_TO_WIN;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	    view.playerAdded(player, howManyPlayers());
	}

	public void nextRound() {
		view.player = players.get(currentPlayer);
		int roll = players.get(currentPlayer).rollDice();
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
			players.get(currentPlayer).addCoin();
			view.correctAnswer(players.get(currentPlayer).coins());
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
		if (currentPlayer == howManyPlayers()) currentPlayer = 0;
	}

	private int howManyPlayers() {
		return players.size();
	}
}