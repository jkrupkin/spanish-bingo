package editor.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shared.FileHandler;
import shared.Word;

public class EditorUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = 480;
	
	ScrollPane wordScroller;
	JPanel wordPanel, buttons;
	JButton newWordButton, saveSetButton;
	JFileChooser fileChooser;

	public EditorUI() {
		// initial setup
		super("Spanish Bingo Card Set Creator");
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
			wordPanel.add(new WordPanel(this));
			wordPanel.revalidate();
		} else if (b == saveSetButton) {
			ArrayList<Word> wordList = new ArrayList<>();			
			WordPanel[] wpa = (WordPanel[]) wordPanel.getComponents();
			for (WordPanel wp : wpa)
				wordList.add(wp.getWord());
			
			int retrival = fileChooser.showSaveDialog(this);			
			if (retrival == JFileChooser.APPROVE_OPTION) try {
				File f = fileChooser.getSelectedFile();
				
				// TODO: check to make sure that 
				
				// TODO: tell FileHandler to write the file
				FileHandler.writeVocab(wordList, "debug.zip");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void dropListWord(WordPanel wp) {
		wordPanel.remove(wp);
		wordPanel.revalidate();
		
		// TODO panel does not update properly when the last word is removed (erroneous behavior)
	}
}
