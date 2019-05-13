package game.ui.menu;

import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.PlayMode;
import shared.Word;

@SuppressWarnings("serial")
public class MenuUI extends JPanel {
	
	ScrollPane setSelector;
	public JButton startPractice, startBingo;
	private ArrayList<ZipElement> zipFileList;
	
	public MenuUI(PlayMode p) {
		super();
		
		// TODO: decide whether to keep or replace BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// group select area
		setSelector = new ScrollPane();
		setSelector.setLayout(new BoxLayout(setSelector, BoxLayout.PAGE_AXIS));
		
		zipFileList = new ArrayList<>();
		
		
		
		
		// "start practice mode" button
		startPractice = new JButton("START PRACTICE MODE");
		startPractice.addActionListener(p);
		
		
		// all bingo mode settings
		// - grid size
		// - mark incorrect answers option
		
		
		
		
		// "start bingo mode" button
		startBingo = new JButton("START BINGO GAME");
		startBingo.addActionListener(p);
	}
	
	public ArrayList<Word> genWordList() {
		ArrayList<Word> wordList = new ArrayList<Word>();
		for (ZipElement zip : zipFileList)
			if (zip.checkbox.isSelected())
				wordList.addAll(zip.wordList);
		
		return wordList;
	}
	
	public boolean getMarkWrongAnswer() {
		//TODO get if wrong answers will be permanently marked incorrect
		return true;
	}
	
	public int getElementCount() {
		//TODO get the number of elements to be placed in the bingo array
		return 16;
	}
}
