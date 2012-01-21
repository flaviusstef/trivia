package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Scholar {
	
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    
    String category = "";
	private View view;

    public Scholar(View view) {
    	this.view = view;
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
	
	@SuppressWarnings("serial")
	public void askQuestion(Player player) {
		List<LinkedList<String>> questions = new ArrayList<LinkedList<String>>() {{
			add(popQuestions);
			add(scienceQuestions);
			add(sportsQuestions);
			add(rockQuestions);
		}};
		String [] categories = { "Pop", "Science", "Sports", "Rock" };
		
		LinkedList<String> questionSet = questions.get(player.location() %4 );
		category = categories[player.location() %4 ];
		
		view.askQuestion(category, questionSet.removeFirst());
	}
	
	public boolean isAnswerCorrect() {
		Random rand = new Random();
		return (rand.nextInt(9) != 7);
	}
}
