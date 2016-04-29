package searcher;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;

import buildConfig.Config;

public class WordContainer {	
	
	private ArrayList<Long> words;
	
	public WordContainer() {
		words = new ArrayList<Long>();
	}
	
	// Returns true only if the string is valid
	// that is it doesn't contain any characters other than [a-z]
	// the length of the string must be <= 30
	private boolean validateString(String input) {
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
	
	private Long getLongOfString(String input) {
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
	
	public String getStringOfLong(long input) {
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
	
	public boolean addString(String input) {
		if(!validateString(input)) {
			return false;
		}
		Long value = getLongOfString(input);
		words.add(value);
		Config.debug(value.toString());
		return true;
	}
	
	public void completed() {
		Collections.sort(words);
	}
	
	public ArrayList<Long> getWords() {
		return words;
	}
	
	public void serialize() throws Exception {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(Config.DATA_FILE_LOCATION));
		for(Long value : words) {
			dos.writeLong(value);
		}
		dos.close();
	}
	
	public void readFromFile(int n) throws Exception {
		FileChannel inChannel = new RandomAccessFile(Config.DATA_FILE_LOCATION, "r").getChannel();
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
		long[] result = new long[n];
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.get(result);
        for(long value : result) {
        	System.out.println(value);
        	System.out.println("\t Equivalent - " + getStringOfLong(value));
        }
		inChannel.close();
	}
}
