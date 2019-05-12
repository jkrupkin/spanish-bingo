package game;

import javax.swing.JFrame;

// All "main operation" code should be stored in this class
// A static class for main game code
public class PlayMode {
	public static void main(String[] args) {
		new PlayMode();
	}
	
	private enum Mode {
		MAIN_MENU,
		PRACTICE,
		GAME
	}
	
	JFrame window;
	Mode mode;
	
	// TODO: main entrance
	public PlayMode() {
		window = new JFrame();
		
		// create main menu here
		// set "window" to contain main menu
		// set the size and visibility of "window"
		
		window.setVisible(true);
	}
}
