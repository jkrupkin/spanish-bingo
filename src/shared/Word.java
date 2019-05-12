package shared;

import java.awt.Image;
import java.io.File;

public class Word {
	private String word;
	private File audio, pic;
	private Image image;

	public Word(String word, String picturePath, String audioPath) {
		this.word = word;
		pic = new File(picturePath);
		audio = new File(audioPath);
	}
	
	public Word(String word, File pictureFile, File audioFile) {
		this.word = word;
		pic = pictureFile;
		audio = audioFile;
	}
	
	public File getPic() {
		return pic;
	}

	public File getAudioClip() {
		return audio;
	}

	public String getWord() {
		return word;
	}
	
	
}
