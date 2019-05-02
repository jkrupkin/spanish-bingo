package editor.ui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.Word;

public class WordPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Word word;
	JButton delete;
	JLabel text;

	WordPanel(String wordText, String imagePath, String soundPath) {
		super();
		
		this.word = new Word(wordText, imagePath, soundPath);
		
		
		// layout setup
		setLayout(new Flow());
		delete = new JButton("[X]");
		delete.addActionListener(this);
		this.add(delete);
		
		text = new JLabel("   \"" + wordText + "\"  [IMG: \"" + imagePath + "\", SND: \"" + soundPath + "\"]");
		this.add(text);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
