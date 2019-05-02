package editor.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400, HEIGHT = 500;
	
	ScrollPane wordScroller;
	JPanel wordPanel, buttons;
	JButton newWordButton, saveSetButton;
	ButtonListener listener;

	public EditorUI() {
		// initial setup
		super();
		setLayout(new BorderLayout());
		
		// central scrolling area
		wordScroller = new ScrollPane();
		this.add(wordScroller, BorderLayout.CENTER);
		
		wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		wordScroller.add(wordPanel);
		
		// button panel setup
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		this.add(buttons, BorderLayout.PAGE_END);
		
		listener = new ButtonListener();
		
		newWordButton = new JButton("Add New Word");
		newWordButton.addActionListener(listener);
		buttons.add(newWordButton);
		
		saveSetButton = new JButton("Save Set as Zip File");
		saveSetButton.addActionListener(listener);
		buttons.add(saveSetButton);
		
		
		// final initialization
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			JButton b = (JButton) event.getSource();
			if (b == newWordButton)
			{
				// TODO Code for "properly" adding a new word to the set
				// will probably use a popup dialogue window of some sort
				// current version is just debug code
				
				add(new WordPanel("word", "imageLocation", "soundLocation"));
				revalidate();
			}
			else if (b == saveSetButton)
			{
				// TODO code for saving all files to a compressed .zip folder
				
			}
		}
		
	}
}
