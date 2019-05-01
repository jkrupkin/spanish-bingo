package editor.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	WordPanel(String word, String imagePath, String soundPath) {
		super();
		setLayout(new FlowLayout());
		
		// TODO make interface tools actually functional
		this.add(new JCheckBox());
		this.add(new JTextField(word));
		this.add(new JTextField(imagePath));
		this.add(new JTextField(soundPath));
		
		JButton b = new JButton("SND");
		this.add(b);
		b = new JButton("X");
		this.add(b);
	}
}
