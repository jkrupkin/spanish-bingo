package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.ui.bingo.BingoUI;
import game.ui.menu.MenuUI;
import game.ui.practice.PracticeUI;
import shared.Word;

// All "main operation" code should be stored in this class
// A static class for main game code
public class PlayMode implements WindowListener, ActionListener {
	public static void main(String[] args) {
		new PlayMode();
	}
	private enum State {
		MENU,
		PRACTICE,
		BINGO
	};
	
	private JFrame window;
	private MenuUI menu;
	private PracticeUI practice;
	private BingoUI bingo;
	private State state;
	
	// TODO: main entrance
	public PlayMode() {
		window = new JFrame();
		menu = new MenuUI(this);
		practice = new PracticeUI();
		bingo = new BingoUI();
		

		//setModeMenu();
		setModePractice();

		window.addWindowListener(this);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setVisible(true);
	}
	
	private void setModeMenu() {
		window.add(menu);
		//TODO set the size of the window
		state = State.MENU;
	}
	
	private void setModePractice() {
		window.add(practice);
		window.setSize(640, 480);
		state = State.PRACTICE;
	}
	
	private void setModeBingo() {
		window.add(bingo);
		//TODO set the size of the window
		state = State.BINGO;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		ArrayList<Word> wordList = menu.genWordList();
		if (src == menu.startPractice) {
			practice.update(wordList);
			this.setModePractice();
		} else if (src == menu.startBingo) {
			Collections.shuffle(wordList);
			
			int n = menu.getElementCount();
			while (wordList.size() > n)
				wordList.remove(n);
			
			bingo.update(wordList, menu.getMarkWrongAnswer());
			this.setModeBingo();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int i = 0;
		String s = "Exit Practice Mode?";
		
		switch (state) {
		case BINGO:
			s = "Exit Bingo Mode?";
		case PRACTICE:
			Object[] options = {"Exit Program", "Quit to Menu"};
			i = JOptionPane.showOptionDialog(window, s, "Close Game?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);
			break;
			
		default:
			i = JOptionPane.showConfirmDialog(window,
					"Are you sure you want to quit?",
					"Close Game?",
					JOptionPane.YES_NO_OPTION);
		}
		
		if (i == JOptionPane.YES_OPTION)
			System.exit(0);
		else if (i == JOptionPane.NO_OPTION && state != State.MENU)
			setModeMenu();
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
}
