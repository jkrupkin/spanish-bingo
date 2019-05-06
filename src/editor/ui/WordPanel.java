package editor.ui;

import java.awt.Container;
import java.awt.FlowLayout;
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
		this.setLayout(new FlowLayout());
		delete = new JButton("[X]");
		delete.addActionListener(this);
		this.add(delete);
		
		text = new JLabel("   \"" + wordText + "\"  [IMG: \"" + imagePath + "\", SND: \"" + soundPath + "\"]");
		this.add(text);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// this method is only called when "delete" button is pushed
		// it removes the panel from the UI
		Container parent = this.getParent();
		parent.remove(this);
		parent.revalidate();
	}
}
