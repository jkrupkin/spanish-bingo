package game.ui.bingo;

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
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
	
	public void update(ArrayList<Word> wordList, boolean markWrongAnswers) {
		
	}
}
