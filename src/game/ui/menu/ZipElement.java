package game.ui.menu;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.FileHandler;
import shared.Word;

@SuppressWarnings("serial")
public class ZipElement extends JPanel {
	ArrayList<Word> wordList;
	JCheckBox checkbox;
	JLabel title;
	
	public ZipElement(File zip) throws ZipException, IOException, UnsupportedAudioFileException {
		wordList = FileHandler.readVocab(zip.getAbsolutePath());
		
		this.setLayout(new FlowLayout());
		
		checkbox = new JCheckBox();
		this.add(checkbox);
		
		title = new JLabel(zip.getName().substring(0, zip.getName().length()-4) + " (Size: "+wordList.size()+")");
		this.add(title);
	}
}
