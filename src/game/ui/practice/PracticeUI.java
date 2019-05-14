package game.ui.practice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.backend.PlayAudio;
import shared.Word;

@SuppressWarnings("serial")
public class PracticeUI extends JPanel implements ActionListener {
	private JLabel cardImage;
	private JButton wordLabel, goLeft, goRight, shuffle;
	private JPanel center;
	
	private ArrayList<Word> wordList;
	private int index;
	
	
	public PracticeUI() {
		super();		
		this.setLayout(new BorderLayout());
		
		center = new JPanel();
			wordLabel = new JButton("SETUP");
			wordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			center.add(wordLabel, BorderLayout.PAGE_START);
			cardImage = new JLabel();
			center.add(cardImage);
		this.add(center, BorderLayout.CENTER);
		
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
			if (index == 0)
				index = wordList.size();
			--index;
			update();
		} else if (source == goRight) {
			++index;
			if (index == wordList.size())
				index = 0;
			update();
		} else if (source == shuffle) {
			Collections.shuffle(wordList);
			index = 0;
			update();
		} else if (source == wordLabel || source == cardImage)
			try {
				PlayAudio.play(wordList.get(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void update(ArrayList<Word> wordList) {
		this.wordList = wordList;
		index = 0;
		update();
	}
	
	private void update() {
		Word cw = wordList.get(index);
		wordLabel.setText(cw.getWord());
		cardImage.prepareImage(cw.getImage(), this.getTopLevelAncestor());
		this.revalidate();
	}
}
