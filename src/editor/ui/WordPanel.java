package editor.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shared.Word;

public class WordPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private EditorUI editor;
	private JTextField wordName;
	private JLabel imageFileName, soundFileName;
	private JButton delete, setImage, setSound;
	private JFileChooser fileChooser;
	private File imageFile, soundFile;

	WordPanel(EditorUI e) {
		super();
		editor = e;
		fileChooser = e.fileChooser;
		
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
				File newFile = fileChooser.getSelectedFile();
				
				if (source == setImage) { 
					if (isSupportedImageFile(newFile))
						imageFileName.setText(imageFile.getName());
					else
						JOptionPane.showMessageDialog(this,
								"Illegal file type chosen!  Legal image types: .jpg, .png, .bmp, .gif");
				} else if (source == setSound) {
					if (isSupportedSoundFile(newFile))
						soundFileName.setText(soundFile.getName());
					else
						JOptionPane.showMessageDialog(this,
								"Illegal file type chosen!  Legal audio types: .wav, .aiff, .au");
				}
				
				this.revalidate();
			}
		}
	}
	
	private boolean isSupportedImageFile(File newFile) {
		String fileName = newFile.getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.'));
		
		String[] legalTypes = {".bmp", ".gif", ".jpg", ".png"};
		
		for (String s : legalTypes)
			if (fileType.equalsIgnoreCase(s))
				return true;
		
		return false;
	}
	
	private boolean isSupportedSoundFile(File newFile) {
		String fileName = newFile.getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.'));
		
		String[] legalTypes = {".aiff", ".au", ".wav"};
		
		for (String s : legalTypes)
			if (fileType.equalsIgnoreCase(s))
				return true;
		
		return false;
	}
	
	public Word getWord() throws IOException, UnsupportedAudioFileException {
		return new Word(wordName.getText(), imageFile, soundFile);
	}
}
