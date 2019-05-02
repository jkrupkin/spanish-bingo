package shared;

import java.io.File;

public class Word {
	String word;
	private File pic;
	private File audioClip;
	
	public Word(String word,String picturePath,String audioPath) {
		this.word = word;
		pic = new File(picturePath);
		audioClip = new File(audioPath);
	}
	
	public File getPic() {
		return pic;
	}

	public File getAudioClip() {
		return audioClip;
	}

	public String getWord() {
		return word;
	}
	
	
}
