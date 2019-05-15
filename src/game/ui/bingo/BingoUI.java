package game.ui.bingo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import shared.Word;

@SuppressWarnings("serial")
public class BingoUI extends JPanel implements ActionListener {
	
	JPanel grid;
	
	public BingoUI() {
		super();
		
		this.setLayout(new BorderLayout());
		
		grid = new JPanel();
		grid.setLayout(new GridLayout());
		// grid panel exists
		// however, buttons are only created/added once the game knows how many it will need
		// so that takes place during update(), below
		this.add(grid, BorderLayout.CENTER);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
	
	public void update(ArrayList<Word> wordList, boolean markWrongAnswers) {
		
	}
}
