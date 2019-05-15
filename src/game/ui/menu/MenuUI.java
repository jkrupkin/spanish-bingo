package game.ui.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
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
		zipFilePanel.setLayout(new GridLayout());
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
		String[] str = {"4x4 Bingo Grid", "5x5 Bingo Grid", "6x6 Bingo Grid"};
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
	
	// Attempt to rework the selection UI to be more visually pleasing
	// Currently scrapped
	/*public MenuUI(PlayMode p) {
		this.setLayout(new BorderLayout());
			JPanel inner = new JPanel();
				ScrollPane setSelector = new ScrollPane();
					JPanel zipFilePanel = new JPanel();
					zipFilePanel.setLayout(new BoxLayout(zipFilePanel, BoxLayout.PAGE_AXIS));
					zipFileList = new ArrayList<>();
					ArrayList<File> fl = FileHandler.zipsInDir("");
					for (File f : fl) try {
						ZipElement z = new ZipElement(f);
						zipFileList.add(z);
						zipFilePanel.add(z);
					} catch (Exception e) {
						e.printStackTrace();
					}
				setSelector.add(zipFilePanel);
			inner.add(setSelector, BorderLayout.CENTER);
				// bingo options UI
				JPanel optionPanel = new JPanel();
				optionPanel.setLayout(new FlowLayout());
					// "mark incorrect answers" option
				optionPanel.add(wrongAnswerCheckbox = new JCheckBox());
				optionPanel.add(new JLabel("Permanently Disable Incorrect Answers"));
					// set bingo grid size
					String[] str = {"4x4 Bingo Grid", "5x5 Bingo Grid", "6x6 Bingo Grid"};
					gridSizeSelector = new JComboBox<>(str);
				optionPanel.add(gridSizeSelector);
			//inner.add(optionPanel, BorderLayout.PAGE_END);
		this.add(inner, BorderLayout.CENTER);
		
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
			// "start practice mode" button
			startPractice = new JButton("START PRACTICE MODE");
			startPractice.addActionListener(p);
			buttons.add(startPractice);
			
			startBingo = new JButton("START BINGO GAME");
			startBingo.addActionListener(p);
			buttons.add(startBingo);
		this.add(buttons, BorderLayout.PAGE_END);
			
	}*/
	
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
		return gridSizeSelector.getSelectedIndex() + 4;
	}
	
	public JButton startPractice() {
		return startPractice;
	}
	public JButton startBingo() {
		return startBingo;
	}
}
