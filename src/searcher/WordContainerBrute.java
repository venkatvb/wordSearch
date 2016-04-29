package searcher;

import java.util.ArrayList;

public class WordContainerBrute implements Search {
	
	ArrayList<String> words;
	
	public WordContainerBrute() {
		words = new ArrayList<String>();
	}
	
	public int count() {
		return this.words.size();
	}
	
	@Override
	public boolean isPresent(String s) {
		for(String word : words) {
			if(s.equals(word)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addString(String s) {
		s = s.trim();
		boolean valid = StringHelper.validateString(s);
		if(!valid) {
			return false;
		}
		words.add(s);
		return true;
	}
}
