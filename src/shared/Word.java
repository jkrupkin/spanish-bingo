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
import org.json.JSONPointer;

public class Word {
	private String word;
	private File audio, pic;
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
		for(int i=0; i < wordsJSON.length(); i++) {
			JSONObject wordJSON = wordsJSON.getJSONObject(i);
			String currentWord = wordJSON.getString("word");
			if(currentWord.equals(word))
				break;
			
		}
		
		
	}
	
	protected File getPic() {
		return pic;
	}

	protected File getAudioClip() {
		return audio;
	}
	public Image getImage() {
		return imageData;
	}

	public String getWord() {
		return word;
	}
	private InputStream extractFile(String file) throws IOException {
		ZipEntry ze = zip.getEntry(file);
		InputStream is = zip.getInputStream(ze);
		return is;
		
	}
	
	
}
