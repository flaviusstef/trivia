package com.adaptionsoft.games.trivia;

public class View {
	
	public String playerName;

	public void playerAdded(String playerName, int totalPlayers) {
		System.out.println(playerName + " was added");
	    System.out.println("They are player number " + totalPlayers);
	}

	public void gettingOutOfPenaltyBox() {
		System.out.printf("%s is getting out of the penalty box\n", playerName);
	}

	public void notGettingOutOfPenaltyBox() {
		System.out.println(playerName + " is not getting out of the penalty box");
	}

	void playerRolled(int roll) {
		System.out.println(playerName + " is the current player");
		System.out.println("They have rolled a " + roll);
	}

	void movedToLocation(int location) {
		String message = "%s's new location is %d\n";
		System.out.printf(message, playerName, location);
	}

	public void incorrectAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(playerName + " was sent to the penalty box");
	}

	public void askQuestion(String category, String question) {
		System.out.println("The category is " + category);
		System.out.println(question);
	}

	public void correctAnswer(int totalPoints) {
		System.out.println("Answer was correct!!!!");
		System.out.println(String.format("%s now has %d Gold Coins.", playerName, totalPoints));
	}
}
