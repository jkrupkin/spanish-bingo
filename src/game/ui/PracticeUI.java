package game.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.PlayMode;

@SuppressWarnings("serial")
public class PracticeUI extends JPanel implements ActionListener {
	PlayMode main;
	JPanel center;
	JButton goLeft, goRight, shuffle;
	
	PracticeUI() {
		super();		
		this.setLayout(new BorderLayout());
		
		center = new JPanel();
		// TODO center setup (display image
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
			// TODO go back one card
		} else if (source == goRight) {
			// TODO go forwards one card
		} else if (source == shuffle) {
			// TODO shuffle the deck
		}
	}
}
