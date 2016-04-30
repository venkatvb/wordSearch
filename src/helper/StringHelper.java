package helper;

import java.util.Random;

public class StringHelper {

	public static String generateString(Random rng, String characters, int length) {
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++) {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static Long getLongOfString(String input) {
		long result = 0;
		char inputArray[] = input.toCharArray(); 
		int shiftTimes = 55;
		for(int i=0; i<input.length(); i++ ) {
			long value = inputArray[i] - 'a' + 1;
			result |= ( (value) << shiftTimes );
			shiftTimes -= 5;
		}
		return result;
	}
	
	public static String getStringOfLong(long input) {
		String result = "";
		for(int i=60; i>=0; i-= 5 ) {
			int value = 0;
			long one = 1;
			long zero = 0;
			if((input & (one << (i - 1))) != zero) {
				value += 16;
			}
			if((input & (one << (i - 2))) != zero) {
				value += 8;
			}
			if((input & (one << (i - 3))) != zero) {
				value += 4;
			}
			if((input & (one << (i - 4))) != zero) {
				value += 2;
			}
			if((input & (one << (i - 5))) != zero) {
				value += 1;
			}
			if(value == zero) {
				break;
			}
			result += (char) (value + 'a' - 1) ;
		}
		return result;
	}
	
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


