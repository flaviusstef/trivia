package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    
    public Game(){
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
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				
				moveToNextPlace(roll);
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
			moveToNextPlace(roll);
			askQuestion();
		}
		
	}

	private void moveToNextPlace(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
		
		String message = "%s's new location is %d";
		System.out.println(String.format(message, players.get(currentPlayer), places[currentPlayer]));
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
		System.out.println("The category is " + currentCategory);
		System.out.println(question);
	}
	
	public void wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
			selectNextPlayer();
			return;
		}
		
		playerGaveCorrectAnswer();
		if (playerWon()) {
			return;
		}
		selectNextPlayer();
	}

	private void playerGaveCorrectAnswer() {
		playerWinsAPoint();
		System.out.println("Answer was correct!!!!");
		System.out.println(String.format("%s now has %d Gold Coins.", players.get(currentPlayer), playerPoints()));
	}

	private void playerWinsAPoint() {
		purses[currentPlayer]++;
	}
	
	private int playerPoints() {
		return purses[currentPlayer];
	}
	
	public void wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		putCurrentPlayerInPenaltyBox();
		
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
