package editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor {
	private static JFrame window;
	private static int width, height, border;
	
	// TODO: Main method for editor program
	public static void main(String[] args) {
		window = new JFrame();
		width = 400;
		height = 500;
		border = 5;
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new BorderLayout());
		
		ScrollPane wordScroller = new ScrollPane();
		JPanel wordPanel = new JPanel();
		
		wordScroller.add(wordPanel);
		mainMenu.add(wordScroller, BorderLayout.CENTER);
		
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
}