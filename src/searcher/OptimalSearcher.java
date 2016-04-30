package searcher;

import helper.StringHelper;

import java.io.RandomAccessFile;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;

import brutesearcher.LinearSearcher;

public class OptimalSearcher implements Searchable{
	
	private ArrayList<Long> words;
	public static final String CLASS_NAME = LinearSearcher.class.getSimpleName();
	
	public OptimalSearcher(String address, int n) {
		words = new ArrayList<>();
		readFromFile(address, n);
		Collections.sort(words);
	}
	
	private void readFromFile(String address, int n) {
		try {
			@SuppressWarnings("resource")
			FileChannel inChannel = new RandomAccessFile(address, "r").getChannel();
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			long[] result = new long[n];
	        LongBuffer longBuffer = buffer.asLongBuffer();
	        longBuffer.get(result);
	        for(Long r : result) {
	        	words.add(r);
	        }
			inChannel.close();
		} catch(Exception e) {
			System.out.println("Error at readFromFile : " + CLASS_NAME + e);
		}
	}
	
	public boolean search(String s) {
		Long value = StringHelper.getLongOfString(s);
		int index = Collections.binarySearch(words, value);
		System.out.println("The index is " + index);
		return index >= 0;
	}
}
