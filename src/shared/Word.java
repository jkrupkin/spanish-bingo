package shared;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.json.JSONArray;
import org.json.JSONObject;


public class Word {
	private String word;
	private File audio, pic; //These are only used when writing to the zip
	private ZipFile zip;
	private Image imageData;
	private AudioInputStream audioData;
	
//Constructor for reading and writing
	public Word(String word, String picturePath, String audioPath) throws IOException, UnsupportedAudioFileException {
		this.word = word;
		pic = new File(picturePath);
		audio = new File(audioPath);
		imageData = ImageIO.read(pic);
		audioData = AudioSystem.getAudioInputStream(audio);
	}
//Constructor for read-only usage from a zip
	public Word(String word, String zipStr) throws IOException, UnsupportedAudioFileException {
		this.word = word;
		zip = new ZipFile(zipStr);
		JSONArray wordsJSON = FileHandler.readJSON(zip);
		JSONObject wordJSON = null;
		for(int i=0; i < wordsJSON.length(); i++) {
			JSONObject currWord = wordsJSON.getJSONObject(i);
			String currentWord = currWord.getString("word");
			if(currentWord.equals(word)) {
				wordJSON = currWord;
				break;
			}
		}
		String imagePath = wordJSON.getString("image");
		String audioPath = wordJSON.getString("audio");
		InputStream is = extractFile(imagePath);
		imageData = ImageIO.read(is);
		BufferedInputStream as = new BufferedInputStream(extractFile(audioPath));
		audioData = AudioSystem.getAudioInputStream(as);
		
	}
	//Used for FileHandler
	protected File getPic() {
		return pic;
	}
	//Used for FileHandler
	protected File getAudioClip() {
		return audio;
	}
	//Retrieves the raw image data
	public Image getImage() {
		return imageData;
	}
	public AudioInputStream getAudio() {
		return audioData;
	}

	public String getWord() {
		return word;
	}
	//Extracts file from associated zip file(if used with constructor)
	private InputStream extractFile(String file) throws IOException {
		ZipEntry ze = zip.getEntry(file);
		InputStream is = zip.getInputStream(ze);
		return is;
		
	}
	
	
}
