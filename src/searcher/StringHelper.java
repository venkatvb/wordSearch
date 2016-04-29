package searcher;

public class StringHelper {
	// Returns true only if the string is valid
	// that is it doesn't contain any characters other than [a-z]
	// the length of the string must be <= 30
	public static boolean validateString(String input) {
		if(input.length() > 12) {
			return false;
		}
		char ch[] = input.toCharArray();
		for(int i=0; i<input.length(); i++ ) {
			if(ch[i] >= 'a' && ch[i] <= 'z') {
				continue;
			}
			return false;
		}
		return true;
	}
}
