package game.ui.menu;

import java.awt.ScrollPane;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import game.PlayMode;
import shared.FileHandler;
import shared.Word;

@SuppressWarnings("serial")
public class MenuUI extends JPanel {
	
	ScrollPane setSelector;
	JButton startPractice, startBingo;
	JComboBox<String> gridSizeSelector;
	private ArrayList<ZipElement> zipFileList;
	
	public MenuUI(PlayMode p) {
		super();
		
		//TODO decide whether to keep or replace BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// group select area
		setSelector = new ScrollPane();		
		zipFileList = new ArrayList<>();
		ArrayList<File> fl = FileHandler.zipsInDir("");
		for (File f : fl) try {
			ZipElement z = new ZipElement(f);
			zipFileList.add(z);
			setSelector.add(z);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.add(setSelector);
		
		// "start practice mode" button
		startPractice = new JButton("START PRACTICE MODE");
		startPractice.addActionListener(p);
		this.add(startPractice);
		
		
		// bingo mode grid size
		String[] str = {"4x4", "5x5", "6x6"};
		gridSizeSelector = new JComboBox<>(str);
		this.add(gridSizeSelector);
		
		// bingo mode incorrect answers
		
		
		
		
		// "start bingo mode" button
		startBingo = new JButton("START BINGO GAME");
		startBingo.addActionListener(p);
		this.add(startBingo);
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
	
	public JButton startPractice() {
		return startPractice;
	}
	public JButton startBingo() {
		return startBingo;
	}
}
