package game.ui;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import shared.Word;

@SuppressWarnings("serial")
public class MenuUI extends JPanel implements ActionListener {
	
	ScrollPane setSelector;
	JButton startPractice, startBingo;
	
	public MenuUI(ActionListener target) {
		super();
		
		// TODO: decide whether to keep or replace BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// group select area
		setSelector = new ScrollPane();
		
		
		// "start practice mode" button
		startPractice = new JButton("START PRACTICE MODE");
		startPractice.addActionListener(target);
		
		
		// all bingo mode settings
		// - grid size
		// - mark incorrect answers option
		
		
		
		
		// "start bingo mode" button
		startBingo = new JButton("START BINGO GAME");
		startBingo.addActionListener(target);
		
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
