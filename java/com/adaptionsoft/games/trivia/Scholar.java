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
    
    String lastCategory = "";

    public Scholar() {
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
	public String generateQuestion(int position) {
		List<LinkedList<String>> questions = new ArrayList<LinkedList<String>>() {{
			add(popQuestions);
			add(scienceQuestions);
			add(sportsQuestions);
			add(rockQuestions);
		}};
		String [] categories = { "Pop", "Science", "Sports", "Rock" };
		
		LinkedList<String> questionSet = questions.get(position %4 );
		lastCategory = categories[position %4 ];
		
		return questionSet.removeFirst();
	}
	
	public String lastCategory() {
		return lastCategory;
	}
	
	public boolean correctAnswer() {
		Random rand = new Random();
		return rand.nextInt(9) == 7;
	}
}
