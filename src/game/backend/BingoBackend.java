package game.backend;

import shared.Word;

public class BingoBackend {

	public boolean compare(Word w1, Word w2) {
		return w1.getWord().equals(w2.getWord());
	}
}
