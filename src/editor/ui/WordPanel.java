package editor.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shared.Word;

public class WordPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	EditorUI editor;
	JTextField wordName;
	JLabel imageFileName, soundFileName;
	JButton delete, setImage, setSound;
	JFileChooser fileChooser;
	File imageFile, soundFile;

	WordPanel(EditorUI e) {
		super();
		editor = e;
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		// layout setup
		this.setLayout(new FlowLayout());
		delete = new JButton("[X]");
		delete.addActionListener(this);
		this.add(delete);
		
		wordName = new JTextField("New Word");
		this.add(wordName);
		
		setImage = new JButton("[IMG]");
		setImage.addActionListener(this);
		this.add(setImage);
		
		imageFileName = new JLabel("<IMAGE NOT SET>");
		this.add(imageFileName);
		
		setSound = new JButton("[SND]");
		setSound.addActionListener(this);
		this.add(setSound);
		
		soundFileName = new JLabel("<SOUND NOT SET>");
		this.add(soundFileName);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == delete) { // delete word from list
			editor.dropListWord(this);
		} else { // select file for word
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				if (source == setImage)
					imageFile = fileChooser.getSelectedFile();
				else if (source == setSound)
					soundFile = fileChooser.getSelectedFile();
			}
		}
	}
	
	public Word getWord() {
		return new Word(wordName.getText(), imageFile, soundFile);
	}
}
