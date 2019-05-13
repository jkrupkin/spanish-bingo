package game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.ui.BingoUI;
import game.ui.MenuUI;
import game.ui.PracticeUI;

// All "main operation" code should be stored in this class
// A static class for main game code
public class PlayMode implements WindowListener {
	public static void main(String[] args) {
		new PlayMode();
	}
	
	private enum State {
		MENU,
		PRACTICE,
		BINGO
	};
	
	JFrame window;
	MenuUI menu;
	PracticeUI practice;
	BingoUI bingo;
	State state;
	
	// TODO: main entrance
	public PlayMode() {
		window = new JFrame();
		menu = new MenuUI();
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}
