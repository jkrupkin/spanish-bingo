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
import javax.swing.JTextField;

public class EditorUI extends JFrame {
	public EditorUI() {
		super();
		
		setLayout(new BorderLayout());
		
		ScrollPane wordScroller = new ScrollPane();
		this.add(wordScroller, BorderLayout.CENTER);
		
		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		wordScroller.add(wordPanel);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		this.add(buttons, BorderLayout.PAGE_END);
		
		JButton b = new JButton("Add New Word");
		b.addActionListener(new AddWordListener(wordPanel));
		buttons.add(b);
		
		b = new JButton("Save Set as Zip File");
		buttons.add(b);
		
		this.setSize(400, 500);
		this.setVisible(true);
	}
	
	private static class AddWordListener implements ActionListener {
		JPanel target;
		
		AddWordListener(JPanel target) {
			this.target = target;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code for "properly" adding a new word to the set
			// will probably use a popup dialogue window of some sort
			// current version is just debug code
			
			target.add(new WordPanel("word", "imageLocation", "soundLocation"));
			target.revalidate();
		}
	}
	
	private static class DropWordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
