package editor.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shared.FileHandler;
import shared.Word;

public class EditorUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400, HEIGHT = 500;
	
	ScrollPane wordScroller;
	JPanel wordPanel, buttons;
	JButton newWordButton, saveSetButton;

	public EditorUI() {
		// initial setup
		super();
		setLayout(new BorderLayout());
		
		// central scrolling area
		wordScroller = new ScrollPane();
		this.add(wordScroller, BorderLayout.CENTER);
		
		wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		wordScroller.add(wordPanel);
		
		// button panel setup
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		this.add(buttons, BorderLayout.PAGE_END);
		
		newWordButton = new JButton("Add New Word");
		newWordButton.addActionListener(this);
		buttons.add(newWordButton);
		
		saveSetButton = new JButton("Save Set as Zip File");
		saveSetButton.addActionListener(this);
		buttons.add(saveSetButton);
		
		
		// final initialization
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton b = (JButton) event.getSource();
		if (b == newWordButton) {
			// TODO Code for "properly" adding a new word to the set
			// will probably use a popup dialogue window of some sort
			// current version is just debug code
			
			wordPanel.add(new WordPanel("word", "imageLocation", "soundLocation"));
			wordPanel.revalidate();
		} else if (b == saveSetButton) {
			ArrayList<Word> wordList = new ArrayList<>();
			
			WordPanel[] wpa = (WordPanel[]) wordPanel.getComponents();
			for (WordPanel wp : wpa) {
				wordList.add(wp.word);
			}
			
			// TODO get card set name (for name of zip file)
			String s = "";
			
			try {
				FileHandler.writeVocab(wordList, s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
