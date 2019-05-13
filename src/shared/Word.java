package shared;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;


public class Word {
	private String word;
	private File audio, pic; //These are only used when writing to the zip
	private ZipFile zip;
	private Image imageData;
	
//Constructor for reading and writing
	public Word(String word, String picturePath, String audioPath) throws IOException {
		this.word = word;
		pic = new File(picturePath);
		audio = new File(audioPath);
		imageData = ImageIO.read(pic);
	}
//Constructor for read-only usage from a zip
	public Word(String word, String zipStr) throws IOException {
		this.word = word;
		zip = new ZipFile(zipStr);
		JSONArray wordsJSON = FileHandler.readJSON(zip);
		JSONObject wordJSON = null;
		for(int i=0; i < wordsJSON.length(); i++) {
			JSONObject currWord = wordsJSON.getJSONObject(i);
			String currentWord = wordJSON.getString("word");
			if(currentWord.equals(word)) {
				wordJSON = currWord;
				break;
			}
		}
		String imagePath = wordJSON.getString("image");
		InputStream is = extractFile(imagePath);
		imageData = ImageIO.read(is);
		
		
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
