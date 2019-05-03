package shared;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.zip.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class FileHandler {

	// TODO Creates .zip file from given ArrayList
	public void writeVocab(ArrayList<Word> words) {
		String json = createJSON(words);
		System.out.println(json);
		File audioDir = new File("audio");
		File imageDir = new File("images");
		audioDir.mkdir();
		imageDir.mkdir();
	}
	// TODO Creates an ArrayList of Words from given .zip file
	public ArrayList<Word> readVocab(File zip)
	{
		
		return new ArrayList<Word>(); //TODO: Change return variable
	}
	private String createJSON(ArrayList<Word> words) {

		JSONArray wordsJSON = new JSONArray();
		for(Word w : words) {
			JSONObject word = new JSONObject();
			word.put("word", w.getWord());
			word.put("image", w.getPic().getPath());
			word.put("audio", w.getAudioClip().getPath());
			wordsJSON.put(word);
		}
		
		return wordsJSON.toString();
	}
}
