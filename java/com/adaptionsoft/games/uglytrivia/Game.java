package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {
    ArrayList<String> players = new ArrayList<String>();
    int[] places = {0,0,0,0,0,0};
    int[] purses  = {0,0,0,0,0,0};
    boolean[] inPenaltyBox  = {false, false, false, false, false, false};
    
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
	private View view;
    
    public Game(View v){
    	this.view = v;
    	generateQuestions();
    }

	private void generateQuestions() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
    	}
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public void add(String playerName) {
	    players.add(playerName);
	    view.newPlayer(playerName, howManyPlayers());
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void nextRound() {
		Random rand = new Random();
		int roll = rand.nextInt(5) + 1;
		view.playerRolled(players.get(currentPlayer), roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				view.gettingOutOfPenaltyBox(players.get(currentPlayer));
				
			} else {
				isGettingOutOfPenaltyBox = false;
				view.notGettingOutOfPenaltyBox(players.get(currentPlayer));
				return;
			}
			
		}
		
		moveToNextLocation(roll);
		askQuestion();
		
		if (rand.nextInt(9) == 7) {
			wrongAnswer();
		} else {
			correctAnswer();
		}
	}

	private void moveToNextLocation(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
		
		view.moveToLocation(players.get(currentPlayer), places[currentPlayer]);
	}

	@SuppressWarnings("serial")
	private void askQuestion() {
		List<LinkedList<String>> questions = new ArrayList<LinkedList<String>>() {{
			add(popQuestions);
			add(scienceQuestions);
			add(sportsQuestions);
			add(rockQuestions);
		}};
		String [] categories = { "Pop", "Science", "Sports", "Rock" };
		
		LinkedList<String> questionSet = questions.get(places[currentPlayer] %4 );
		String currentCategory = categories[places[currentPlayer] %4 ];
		
		String question = questionSet.removeFirst();
		view.askQuestion(currentCategory, question);
	}
	
	public void correctAnswer() {
		if (staysInPenaltyBox()) {
			selectNextPlayer();
			return;
		}
		
		playerWinsAPoint();
		view.correctAnswer(players.get(currentPlayer), playerPoints());
		
		if (playerWon()) {
			return;
		}
		selectNextPlayer();
	}

	private boolean staysInPenaltyBox() {
		return inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox;
	}

	private void playerWinsAPoint() {
		purses[currentPlayer]++;
	}
	
	private int playerPoints() {
		return purses[currentPlayer];
	}
	
	public void wrongAnswer(){
		putCurrentPlayerInPenaltyBox();
		view.sentToPenaltyBox(players.get(currentPlayer));
		selectNextPlayer();
	}

	private void putCurrentPlayerInPenaltyBox() {
		inPenaltyBox[currentPlayer] = true;
	}

	private void selectNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private boolean playerWon() {
		return (playerPoints() == 6);
	}
	
	public boolean isGameOver() {
		return playerWon();
	}
}
