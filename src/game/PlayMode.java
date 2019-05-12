package game;

import javax.swing.JFrame;

import game.ui.BingoUI;
import game.ui.MenuUI;
import game.ui.PracticeUI;

// All "main operation" code should be stored in this class
// A static class for main game code
public class PlayMode {
	public static void main(String[] args) {
		new PlayMode();
	}
	
	JFrame window;
	MenuUI menu;
	PracticeUI practice;
	BingoUI bingo;
	
	// TODO: main entrance
	public PlayMode() {
		window = new JFrame();
		menu = new MenuUI();
		practice = new PracticeUI();
		bingo = new BingoUI();
		
		// set "window" to contain main menu
		// set the size and visibility of "window"
		
		setModePractice();
		
		window.setVisible(true);
	}
	
	private void setModePractice() {
		window.add(practice);
		window.setSize(640, 480);
	}
}
