package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game {
    ArrayList<String> players = new ArrayList<String>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
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

	public boolean add(String playerName) {
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
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
		Map<String, LinkedList<String>> questionSets = new HashMap<String, LinkedList<String>>() {{
				put("Pop", popQuestions);
				put("Science", scienceQuestions);
				put("Sports", sportsQuestions);
				put("Rock", rockQuestions);
			}};
			
		String question = questionSets.get(currentCategory()).removeFirst();
		System.out.println("The category is " + currentCategory());
		System.out.println(question);
	}
	
	
	private String currentCategory() {
		String [] categories = {
				"Pop", "Science", "Sports", "Rock",
				"Pop", "Science", "Sports", "Rock",
				"Pop", "Science", "Sports", "Rock"
		};
		
		return categories[places[currentPlayer] %4 ];
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
			selectNextPlayer();
			return true;
		}
		
		playerGaveCorrectAnswer();
		boolean winner = didPlayerWin();
		selectNextPlayer();
		
		return winner;
	}

	private void playerGaveCorrectAnswer() {
		playerWinsAPoint();
		System.out.println("Answer was correct!!!!");
		System.out.println(String.format("%s now has %d Gold Coins.", players.get(currentPlayer), purses[currentPlayer]));
	}

	private void playerWinsAPoint() {
		purses[currentPlayer]++;
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		putCurrentPlayerInPenaltyBox();
		
		selectNextPlayer();
		return true;
	}

	private void putCurrentPlayerInPenaltyBox() {
		inPenaltyBox[currentPlayer] = true;
	}

	private void selectNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
