package game.ui.menu;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.PlayMode;
import shared.FileHandler;
import shared.Word;

@SuppressWarnings("serial")
public class MenuUI extends JPanel {
	
	private ScrollPane setSelector;
	private JButton startPractice, startBingo;
	private JPanel zipFilePanel;
	private JCheckBox wrongAnswerCheckbox;
	private JComboBox<String> gridSizeSelector;
	private ArrayList<ZipElement> zipFileList;
	
	public MenuUI(PlayMode p) {
		super();
		
		//TODO decide whether to keep or replace BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// group select area
		zipFilePanel = new JPanel();
		zipFileList = new ArrayList<>();
		ArrayList<File> fl = FileHandler.zipsInDir("");
		for (File f : fl) try {
			ZipElement z = new ZipElement(f);
			zipFileList.add(z);
			zipFilePanel.add(z);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSelector = new ScrollPane();
		setSelector.add(zipFilePanel);
		this.add(setSelector);
		
		// "start practice mode" button
		startPractice = new JButton("START PRACTICE MODE");
		startPractice.addActionListener(p);
		this.add(startPractice);
		
		// bingo mode grid size selector
		String[] str = {"4x4", "5x5", "6x6"};
		gridSizeSelector = new JComboBox<>(str);
		this.add(gridSizeSelector);
		
		// bingo mode mark incorrect answers
		JPanel wrongAnswerPanel = new JPanel();
		wrongAnswerPanel.setLayout(new FlowLayout());
		wrongAnswerCheckbox = new JCheckBox();
		wrongAnswerPanel.add(wrongAnswerCheckbox);
		wrongAnswerPanel.add(new JLabel("Permanently Disable Incorrect Answers"));
		this.add(wrongAnswerPanel);
		
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
		return wrongAnswerCheckbox.isSelected();
	}
	
	public int getElementCount() {
		int x = gridSizeSelector.getSelectedIndex();
		x += 4;
		return x * x;
	}
	
	public JButton startPractice() {
		return startPractice;
	}
	public JButton startBingo() {
		return startBingo;
	}
}
