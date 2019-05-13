package game.ui.practice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.PlayMode;
import shared.Word;

@SuppressWarnings("serial")
public class PracticeUI extends JPanel implements ActionListener {
	PlayMode main;
	JLabel wordLabel, cardImage;
	JButton goLeft, goRight, shuffle, playAudio;
	JPanel center;
	
	public PracticeUI() {
		super();		
		this.setLayout(new BorderLayout());
		
		center = new JPanel();
		
		wordLabel = new JLabel("SETUP");
		center.add(wordLabel, BorderLayout.PAGE_START);
		wordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		cardImage = new JLabel();
		// TODO center setup (display image
		this.add(cardImage, BorderLayout.CENTER);
		
		goLeft = new JButton("<-");
		goLeft.addActionListener(this);
		this.add(goLeft, BorderLayout.LINE_START);
		
		goRight = new JButton("->");
		goRight.addActionListener(this);
		this.add(goRight, BorderLayout.LINE_END);
		
		shuffle = new JButton("Shuffle Cards");
		shuffle.addActionListener(this);
		this.add(shuffle, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == goLeft) {
			// TODO go back one card
		} else if (source == goRight) {
			// TODO go forwards one card
		} else if (source == shuffle) {
			// TODO shuffle the deck
		}
	}

	public void update(ArrayList<Word> wordList) {
		// TODO Auto-generated method stub
		
	}
}
