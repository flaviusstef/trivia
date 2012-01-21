package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int LOCATION_COUNT = 12;
	private static final int COINS_REQUIRED_TO_WIN = 6;
	
	List<Player> players = new ArrayList<Player>();
	Player currentPlayer = null;
	
	private View view;
	private Scholar scholar;
    
    public Game(View v, Scholar sch){
    	this.view = v;
    	this.scholar = sch;
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean isFinished() {
		return currentPlayer.coins() == COINS_REQUIRED_TO_WIN;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
		// player to start is first player added
		if (currentPlayer == null) currentPlayer = player;
	    view.playerAdded(player, howManyPlayers());
	}

	public void nextRound() {
		view.player = currentPlayer;
		int roll = currentPlayer.rollDice();
		view.playerRolled(roll);
		if (currentPlayer.inPenaltyBox()) {
			if (rollIsLiberating(roll)) {
				currentPlayer.releaseFromPenaltyBox();
				view.gettingOutOfPenaltyBox();
			} else {
				view.notGettingOutOfPenaltyBox();
				selectNextPlayer();
				return;
			}
		}
		
		movePlayerToNextLocation(roll);
		scholar.askQuestion(currentPlayer);
		verifyAnswer();
		if (!isFinished()) selectNextPlayer();
	}

	private void verifyAnswer() {
		if (scholar.isAnswerCorrect()) {
			currentPlayer.receiveCoin();
			view.correctAnswer(currentPlayer.coins());
		} else {
			currentPlayer.sendToPenaltyBox();
			view.incorrectAnswer();
		}
	}

	private void movePlayerToNextLocation(int roll) {
		int newLocation = (currentPlayer.location() + roll) % LOCATION_COUNT;
		currentPlayer.moveTo(newLocation);
		view.movedToLocation(newLocation);
	}

	private void selectNextPlayer() {
		int nextPlayer = players.indexOf(currentPlayer) + 1;
		if (nextPlayer == howManyPlayers())
			nextPlayer = 0;
		currentPlayer = players.get(nextPlayer);
	}

	private int howManyPlayers() {
		return players.size();
	}
	
	private boolean rollIsLiberating(int roll) {
		return roll %2 != 0;
	}
}