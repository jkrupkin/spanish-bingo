package game.ui.bingo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.backend.PlayAudio;
import shared.Word;

@SuppressWarnings("serial")
public class BingoUI extends JPanel implements ActionListener {
	private JPanel grid;
	private JButton[][] boardButtons;
	private Word[][] wordBank;
	private boolean[][] wordGuessed;
	private JButton replayAudio;
	private ArrayList<Word> wordList;
	private Word currentWord;
	private boolean markWrongAnswers, inGame;
	
	public BingoUI() {
		super();
		this.setLayout(new BorderLayout());
		
		grid = new JPanel();
		// grid panel exists
		// however, buttons are only created/added once the game knows how many it will need
		// so that takes place during update(), below
		this.add(grid, BorderLayout.CENTER);
		
		// "replay audio" button
		replayAudio = new JButton("Hear Word Again");
		replayAudio.addActionListener(this);
		this.add(replayAudio, BorderLayout.PAGE_END);
	}
	public void update(ArrayList<Word> wordList, int size, boolean markWrongAnswers) {
		this.wordList = wordList;
		wordBank = new Word[size][size];
		wordGuessed = new boolean[size][size];
		boardButtons = new JButton[size][size];
		this.markWrongAnswers = markWrongAnswers;
		
		grid.removeAll();
		grid.setLayout(new GridLayout(size, size));
		for (int i = 0; i < boardButtons.length; ++i) {
			for (int j = 0; j < boardButtons[i].length; ++j) {
				JButton b = new JButton();
				boardButtons[i][j] = b;
				Word w = wordList.get(i * boardButtons.length + j);
				wordBank[i][j] = w;
				wordGuessed[i][j] = false;
				
				b.setActionCommand(i+" "+j);
				b.addActionListener(this);
				grid.add(b);
				b.setIcon(new ImageIcon(w.getScaledImage(150, 150)));
			}
		}
		
		Collections.shuffle(wordList);
		inGame = getNewWord();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (!inGame)
			return;
		
		if (event.getSource() == replayAudio) {
			if (!PlayAudio.isPlaying())
				try {
					PlayAudio.play(currentWord);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else { // action input
			String[] str = event.getActionCommand().split(" ");
			int i, j;
			i = Integer.parseInt(str[0]);
			j = Integer.parseInt(str[1]);
			Word w = wordBank[i][j];
			if (w == currentWord) {
				wordGuessed[i][j] = true;
				boardButtons[i][j].setEnabled(false);
				boardButtons[i][j].setText(currentWord.getWord());
				
				// check if bingo has been scored
				if (checkBingo()) {
					// you won at bingo!
					replayAudio.setText("You won!  Congradulations!");
					// TODO disable all buttons
					replayAudio.setEnabled(false);
					for (int m = 0; m < boardButtons.length; ++m) {
						for (int n = 0; n < boardButtons[m].length; ++n) {
							boardButtons[m][n].setEnabled(false);
						}
					}
					return;
				}
				
				// fix incorrect answers (if option is chosen)
				if (!markWrongAnswers) {
					for (int m = 0; m < boardButtons.length; ++m) {
						for (int n = 0; n < boardButtons[m].length; ++n) {
							if (!wordGuessed[m][n]) {
								boardButtons[m][n].setEnabled(true);
							}
						}
					}
				}
				
				if (!getNewWord()) {
					// game has been lost (no more words!)
					inGame = false;
					replayAudio.setText("You lost...");
					replayAudio.setEnabled(false);
					return;
				}
			} else {
				boardButtons[i][j].setEnabled(false);
				if (markWrongAnswers) {
					wordList.remove(wordBank[i][j]);
				}
			}
		}
	}
	
	private boolean getNewWord() {
		if (wordList.isEmpty())
			return false;
		currentWord = wordList.get(0);
		wordList.remove(currentWord);
		return true;
	}
	
	private boolean checkBingo() {
		boolean d1, d2;
		d1 = true;
		d2 = true;
		for (int i = 0; i < wordGuessed.length; ++i) {
			boolean v, h;
			v = true;
			h = true;
			for (int j = 0; j < wordGuessed.length; ++j) {
				v &= wordGuessed[i][j];
				h &= wordGuessed[j][i];
			}
			if (v || h)
				return true;
			d1 &= wordGuessed[i][i];
			d2 &= wordGuessed[i][wordGuessed.length-i-1];
		}
		if (d1 || d2)
			return true;
		return false;
	}
}
