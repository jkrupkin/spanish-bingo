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
	private static EditorUI ui;
	
	// entry method for editor program
	public static void main(String[] args) {
		
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