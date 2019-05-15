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
	private Image imageData, scaleImage;
	private AudioInputStream audioData;
	private boolean isZip;
	private int scaleWidth, scaleHeight;
	
//Constructor for reading and writing
	public Word(String word, String picturePath, String audioPath) throws IOException, UnsupportedAudioFileException {
		this(word, new File(picturePath), new File(audioPath));
	}
	public Word(String word, File pictureFile, File audioFile) throws IOException, UnsupportedAudioFileException {
		this.word = word;
		pic = pictureFile;
		audio = audioFile;
		imageData = ImageIO.read(pic);
		this.scaleImage();
		isZip = false;
	}
//Constructor for read-only usage from a zip
	public Word(String word, String zipStr) throws IOException, UnsupportedAudioFileException {
		this.word = word;
		zip = new ZipFile(zipStr);
		JSONObject wordJSON = getJSON();
		String imagePath = wordJSON.getString("image");
		InputStream is = extractFile(imagePath);
		imageData = ImageIO.read(is);
		this.scaleImage();
		isZip = true;
	}
	private void scaleImage() {
		scaleWidth = imageData.getWidth(null);
		scaleHeight = imageData.getWidth(null);
		scaleImage = imageData;
	}
	private JSONObject getJSON() throws IOException {
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
		return wordJSON;
	
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
	//Resets the audio stream after playback. This needs to be done each time audio is played
	public void resetAudioStream() throws IOException, UnsupportedAudioFileException {
		if(isZip) {
		String audioPath = getJSON().getString("audio");
		BufferedInputStream as = new BufferedInputStream(extractFile(audioPath));
		audioData = AudioSystem.getAudioInputStream(as);
		}
		else{
		audioData = AudioSystem.getAudioInputStream(audio);
		}
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
	
	public Image getScaledImage(int tw, int th) {
		int aw, ah;
		aw = imageData.getWidth(null);
		ah = imageData.getHeight(null);
		
		if (aw <= tw && ah <= th)
			return imageData;
		if (tw == scaleWidth && th == scaleHeight)
			return scaleImage;
		System.out.println("WORD: " + tw + ", " + th);
		System.out.println(scaleImage.getWidth(null)+", "+scaleImage.getHeight(null));
		System.out.println(scaleWidth+", "+scaleHeight);
		
		if ( (tw/(double)aw) > (th/(double)ah) ) 
			scaleImage = imageData.getScaledInstance(tw, -1, Image.SCALE_SMOOTH);
		else
			scaleImage = imageData.getScaledInstance(-1, th, Image.SCALE_SMOOTH);
		
		scaleWidth = tw;
		scaleHeight = th;
		
		return scaleImage;
	}
	
	
}
