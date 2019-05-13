package game.ui;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import shared.Word;

@SuppressWarnings("serial")
public class MenuUI extends JPanel implements ActionListener {
	
	ScrollPane setSelector;
	
	public MenuUI() {
		super();
		
		// group select area
		
		
		// "start practice mode" button
		
		
		// bingo mode settings
		// - grid size
		// - mark incorrect answers
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Auto-generated method stub
		
	}
	
	public ArrayList<Word> getWordList() {
		ArrayList<Word> wordList = new ArrayList<Word>();
		
		//TODO generate list of words based on checked boxes
		
		return wordList;
	}
}
