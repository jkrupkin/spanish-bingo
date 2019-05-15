package game.backend;

import java.util.ArrayList;
import java.util.Collections;

import shared.Word;

public class BingoBackend {
	private enum WordState {
		NONE,
		CORRECT,
		INCORRECT
	}
	private Word[][] words;
	private WordState[][] wordStates;
	
	public BingoBackend(int size, ArrayList<Word> contents) {
		words = new Word[size][size];
		wordStates = new WordState[size][size];
		
		for (int i = 0; i < words.length; ++i) {
			for (int j = 0; j < words[i].length; ++j) {
				
			}
		}
		
	}
	
	public boolean compare(Word w1, Word w2) {
		return w1.getWord().equals(w2.getWord());
	}
	
	public Word getCurrentWord(ArrayList<Word> word) {
		
		Collections.shuffle(word);
		
		return word.get(0);
	}
}
