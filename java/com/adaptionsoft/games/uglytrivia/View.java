package com.adaptionsoft.games.uglytrivia;

public class View {

	public void newPlayer(String playerName, int totalPlayers) {
		System.out.println(playerName + " was added");
	    System.out.println("They are player number " + totalPlayers);
	}

	public void gettingOutOfPenaltyBox(String playerName) {
		System.out.println(playerName + " is getting out of the penalty box");
	}

	public void notGettingOutOfPenaltyBox(String playerName) {
		System.out.println(playerName + " is not getting out of the penalty box");
	}

	void playerRolled(String playerName, int roll) {
		System.out.println(playerName + " is the current player");
		System.out.println("They have rolled a " + roll);
	}

	void moveToLocation(String playerName, int location) {
		String message = "%s's new location is %d";
		System.out.println(String.format(message, playerName, location));
	}

	public void sentToPenaltyBox(String playerName) {
		System.out.println("Question was incorrectly answered");
		System.out.println(playerName + " was sent to the penalty box");
	}

	public void askQuestion(String category, String question) {
		System.out.println("The category is " + category);
		System.out.println(question);
	}

	public void correctAnswer(String player, int totalPoints) {
		System.out.println("Answer was correct!!!!");
		System.out.println(String.format("%s now has %d Gold Coins.", player, totalPoints));
	}

}
