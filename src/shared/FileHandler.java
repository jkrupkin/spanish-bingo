package shared;
import java.io.File;
import java.util.ArrayList;
import java.util.zip.*;
import org.json.*;
public class FileHandler {

	// TODO Creates .zip file from given ArrayList
	public void writeVocab(ArrayList<Word> words) {
		
	}
	// TODO Creates an ArrayList of Words from given .zip file
	public ArrayList<Word> readVocab(File zip)
	{
		
		return new ArrayList<Word>(); //TODO: Change return variable
	}
	private String createJSON(ArrayList<Word> words) {
		ArrayList<String> word = new ArrayList<String>();
		ArrayList<String> imagePath = new ArrayList<String>();
		ArrayList<String> audioPath = new ArrayList<String>();
		for(Word w : words) {
			word.add(w.getWord());
			imagePath.add(w.getPic().getPath());
			audioPath.add(w.getAudioClip().getPath());
			
		}
		return "";
	}
}
