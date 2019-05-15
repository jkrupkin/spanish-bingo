package game.backend;

import java.util.ArrayList;
import java.util.Collections;

import shared.Word;

public class BingoBackend {

	public boolean compare(Word w1, Word w2) {
		return w1.getWord().equals(w2.getWord());
	}
	
	public Word getCurrentWord(ArrayList<Word> word) {
		ArrayList<Word> w = word;
		Collections.shuffle(word);
		
		return word.get(0);
	}
}
