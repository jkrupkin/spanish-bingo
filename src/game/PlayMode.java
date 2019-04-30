package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shared.Word;

// All "main operation" code should be stored in this class
// A static class for main game code
public class PlayMode {
	private static JFrame window;
	private static List<Word> wordList;
	
	// TODO: main program area; main menu setup
	public static void main(String[] args) {
		window = new JFrame();
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new BorderLayout());
		
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(new JButton("Add New Word(s)"));
		buttons.add(new JButton("Save Set as Zip File"));
		mainMenu.add(buttons, BorderLayout.PAGE_END);
		
		window.add(mainMenu);
		
		/*
		 * JFrame
		 * - Center
		 * - - List of cards currently in system
		 * - - - Flow Layout
		 * - - - - Card Name (editable)
		 * - - - - 
		 * - Bottom
		 * - - Flow Layout
		 * - - - Button("Add New Card(s)")
		 * - - - Button("Compress to .zip")
		 */
		
		window.setSize(400, 500);
		window.setVisible(true);
	}
	
	private class mainMenu extends JFrame {
		
	}
	
	// TODO: sub-method for practice window
	private static void practiceMode() {
		
	}
	
	// TODO: sub-method for bingo game window
	private static void bingoGame() {
		
	}
	
	// TODO: filename-getting method
	// reads the source directory, and produces a list of the names for all .zip files
	private static String[] getFilenameList() {
		String[] s = null;
		
		// here be code
		
		return s;
	}
	
	// TODO: file-reading method
	private static List<Word> getWords(String[] chosenGroups) {
		List<Word> l = new LinkedList<>();
		
		// more code
		
		return l;
	}
}
