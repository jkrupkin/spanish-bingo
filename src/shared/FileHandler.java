package shared;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.zip.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class FileHandler {

	// TODO Creates .zip file from given ArrayList
	public static void writeVocab(ArrayList<Word> words, String path) throws IOException {
		///Create the JSON String and directory Structure for .zip file
		String json = createJSON(words);
		System.out.println(json);
		File audioDir = new File("audio");
		File imageDir = new File("images");
		File wordsJSON = new File("words.json");
		audioDir.mkdir();
		imageDir.mkdir();		
		BufferedWriter bw = new BufferedWriter(new FileWriter(wordsJSON));
		bw.write(json);
		bw.close();
		
		FileOutputStream fos = new FileOutputStream(path);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (Word w : words) {
			Path newImagePath = Paths.get("images/" + w.getPic().getName());
			Path originalImagePath = w.getPic().toPath();
			
			Path newAudioPath = Paths.get("audio/" + w.getAudioClip().getName());
			Path originalAudioPath = w.getPic().toPath();
			Files.copy(originalImagePath, newImagePath, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(originalAudioPath, newAudioPath, StandardCopyOption.REPLACE_EXISTING);
			
			toZip("images/" + w.getPic().getName(), zos);
			toZip("audio/" + w.getAudioClip().getName(), zos);
		}
		toZip(wordsJSON.getPath(), zos);
		zos.close();
		fos.close();

		
	}
	private static void toZip(String f, ZipOutputStream zos ) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(f);
		ZipEntry ze = new ZipEntry(f);
		zos.putNextEntry(ze);
		
		byte[] bytes = new byte[1024];
		int length;
		while((length = fis.read(bytes)) >= 0)
			zos.write(bytes,0,length);
		zos.closeEntry();
		fis.close();
		
	}
	
	// TODO Creates an ArrayList of Words from given .zip file
	public static ArrayList<Word> readVocab(File zip) {
		
		return new ArrayList<Word>(); //TODO: Change return variable
	}
	
	private static String createJSON(ArrayList<Word> words) {
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
