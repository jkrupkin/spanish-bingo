package game.ui.bingo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import shared.Word;

@SuppressWarnings("serial")
public class BingoUI extends JPanel implements ActionListener {
	JPanel grid;
	JButton[][] boardButtons;
	JButton replayAudio;
	public BingoUI() {
		super();
		
		this.setLayout(new BorderLayout());
		
		grid = new JPanel();
		// grid panel exists
		// however, buttons are only created/added once the game knows how many it will need
		// so that takes place during update(), below
		this.add(grid, BorderLayout.CENTER);
		
		// "replay audio" button
		replayAudio = new JButton();
		replayAudio.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == replayAudio) {
			// TODO replay audio
		} else {
			// TODO update backend with selected word
		}
	}
	
	public void update(ArrayList<Word> wordList, int size, boolean markWrongAnswers) {
		grid.removeAll();
		grid.setLayout(new GridLayout(size, size));
		boardButtons = new JButton[size][size];
		for (int i = 0; i < boardButtons.length; ++i) {
			for (int j = 0; j < boardButtons[i].length; ++j) {
				JButton b = new JButton();
				boardButtons[i][j] = b;
				b.addActionListener(this);
				grid.add(b);
			}
		}
		
		grid.revalidate();
		
		for (int i = 0; i < boardButtons.length; ++i) {
			for (int j = 0; j < boardButtons[i].length; ++j) {
				Word w = wordList.get(i * boardButtons.length + j);
				JButton b = boardButtons[i][j];
				b.setActionCommand(w.getWord());
				
				int tw, th;
				tw = b.getWidth();
				th = b.getHeight();
				
				System.out.println(tw+", "+th); tw = th = 200; //debug
				b.setIcon(new ImageIcon(w.getScaledImage(tw, th)));
			}
		}
	}
}
