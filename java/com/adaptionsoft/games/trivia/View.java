package com.adaptionsoft.games.trivia;

public class View {
	
	public Player player;

	public void playerAdded(Player player, int totalPlayers) {
		System.out.println(player + " was added");
	    System.out.println("They are player number " + totalPlayers);
	}

	public void gettingOutOfPenaltyBox() {
		System.out.printf("%s is getting out of the penalty box\n", player);
	}

	public void notGettingOutOfPenaltyBox() {
		System.out.println(player + " is not getting out of the penalty box");
	}

	void playerRolled(int roll) {
		System.out.println(player + " is the current player");
		System.out.println("They have rolled a " + roll);
	}

	void movedToLocation(int location) {
		String message = "%s's new location is %d\n";
		System.out.printf(message, player, location);
	}

	public void incorrectAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(player + " was sent to the penalty box");
	}

	public void askQuestion(String category, String question) {
		System.out.println("The category is " + category);
		System.out.println(question);
	}

	public void correctAnswer(int totalPoints) {
		System.out.println("Answer was correct!!!!");
		System.out.println(String.format("%s now has %d Gold Coins.", player, totalPoints));
	}
}
