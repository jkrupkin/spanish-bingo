package shared;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class FileHandler {
	// TODO Creates .zip file from given ArrayList and path
	public static void writeVocab(ArrayList<Word> words, String path) throws IOException {
		///Create the JSON String and directory Structure for .zip file
		JSONArray wordsJSON = new JSONArray();
		File audioDir = new File("audio");
		File imageDir = new File("images");
		File wordsFile = new File("words.json");
		audioDir.mkdir();
		imageDir.mkdir();		

		
		FileOutputStream fos = new FileOutputStream(path);
		ZipOutputStream zos = new ZipOutputStream(fos);
		int i = 0;
		for (Word w : words) {
			String imageExt = w.getPic().getName().substring(w.getPic().getName().indexOf('.'));
			String audioExt = w.getAudioClip().getName().substring(w.getAudioClip().getName().indexOf('.'));
			Path newImagePath = Paths.get("images/" + i + imageExt);
			Path originalImagePath = w.getPic().toPath();
			
			Path newAudioPath = Paths.get("audio/" + i + audioExt);
			Path originalAudioPath = w.getAudioClip().toPath();
			Files.copy(originalImagePath, newImagePath, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(originalAudioPath, newAudioPath, StandardCopyOption.REPLACE_EXISTING);
			
			toZip(newImagePath.toString(), zos);
			toZip(newAudioPath.toString(), zos);
			JSONObject word = new JSONObject();
			word.put("word", w.getWord());
			word.put("image", newImagePath.toString());
			word.put("audio", newAudioPath.toString());
			wordsJSON.put(word);
			i++;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(wordsFile));
		bw.write(wordsJSON.toString());
		bw.close();
		toZip(wordsFile.getPath(), zos);
		zos.close();
		fos.close();
	}
	
	// TODO Returns a list of .zip files from the specified directory (relative or absolute)
	public static ArrayList<File> zipsInDir(String dir){
		File fDir = new File(dir);
		FileFilter zipFilter = new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				if(arg0.getName().endsWith(".zip") && arg0.isFile())
					return true;
				else
					return false;
			}
			
		};
		ArrayList<File> zips = new ArrayList<File>(Arrays.asList(fDir.listFiles(zipFilter)));
		return zips;
		
		
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
	public static ArrayList<Word> readVocab(String zip) throws ZipException, IOException {
		ZipFile zf = new ZipFile(zip);
		ArrayList<Word> words = new ArrayList<Word>();
		JSONArray wordsJSON =readJSON(zf);
		zf.close();
		for(int i = 0; i < wordsJSON.length(); i++) {
			JSONObject word = wordsJSON.getJSONObject(i);
			String wordStr = word.getString("word");
			Word w = new Word(wordStr, zip);
			words.add(w);
			System.out.println(wordStr);
		}
		return words;
	}
	public static JSONArray readJSON(ZipFile zf) throws IOException {
		ZipEntry json = zf.getEntry("words.json");
		InputStream is = zf.getInputStream(json);
		String jsonStr;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
			jsonStr = br.readLine();
		br.close();
		JSONArray wordsJSON = new JSONArray(jsonStr);
		return wordsJSON;
	}
}
