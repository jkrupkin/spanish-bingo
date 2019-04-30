package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor {
	private static JFrame window;
	
	// entry method for editor program
	public static void main(String[] args) {
		// window setup
		window = new JFrame();
		
		window.setLayout(new BorderLayout());
		
		ScrollPane wordScroller = new ScrollPane();
		window.add(wordScroller, BorderLayout.CENTER);
		
		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		wordScroller.add(wordPanel);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		window.add(buttons, BorderLayout.PAGE_END);
		
		JButton b = new JButton("Add New Word(s)");
		b.addActionListener(new AddWordListener(wordPanel));
		buttons.add(b);
		
		b = new JButton("Save Set as Zip File");
		buttons.add(b);
		
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
	
	private static class AddWordListener implements ActionListener {
		JPanel target;
		
		AddWordListener(JPanel target) {
			this.target = target;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			genNewWord("debug");
			target.revalidate();
		}
		
		private void genNewWord(String filePath) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			JTextField t = new JTextField();
			t.setText(filePath);
			panel.add(t);
			JButton b = new JButton("SND");
			panel.add(b);
			b = new JButton("DEL");
			panel.add(b);
			target.add(panel);
		}
	}
	
	private static class WordPanel extends JPanel {
		WordPanel() {
			super();
			setLayout(new FlowLayout());
		}
	}
}