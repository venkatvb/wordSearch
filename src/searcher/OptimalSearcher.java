package searcher;

import helper.StringHelper;

import java.io.RandomAccessFile;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;

import buildConfig.Config;

public class OptimalSearcher implements Searchable{
	
	private ArrayList<Long> words;
	
	public void readFromFile(int n) throws Exception {
		@SuppressWarnings("resource")
		FileChannel inChannel = new RandomAccessFile(Config.DATA_FILE_LOCATION, "r").getChannel();
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
		long[] result = new long[n];
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.get(result);
        for(Long r : result) {
        	words.add(r);
        }
		inChannel.close();
	}
	
	public boolean search(String s) {
		Long value = StringHelper.getLongOfString(s);
		int index = Collections.binarySearch(words, value);
		return index >= 0;
	}
}
