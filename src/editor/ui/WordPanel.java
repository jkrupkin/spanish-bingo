package editor.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordPanel extends JPanel {
	WordPanel(String word, String imagePath, String soundPath) {
		super();
		setLayout(new FlowLayout());
		
		this.add(new JTextField(word));
		this.add(new JTextField(imagePath));
		this.add(new JTextField(soundPath));
		
		JButton b = new JButton("SND");
		this.add(b);
		b = new JButton("X");
		this.add(b);
	}
}
