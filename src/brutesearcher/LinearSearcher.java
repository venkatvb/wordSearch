package brutesearcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import buildConfig.Config;

public class LinearSearcher implements Searchable{
	
	private ArrayList<String> words;
	public static final String CLASS_NAME = LinearSearcher.class.getSimpleName();
	
	public LinearSearcher(String address, int n) {
		words = new ArrayList<>();
		readFromFile(address, n);
		Collections.sort(words);
	}
	
	private void readFromFile(String address, int n) {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			for(int i=0; i<n; i++ ) {
				String s = sc.next();
				words.add(s);
			}
		} catch(Exception e) {
			System.out.println("Error at readFromFile : " + CLASS_NAME + e);
		}
	}
	
	public boolean search(String s) {
		int index = Collections.binarySearch(words, s);
		Config.debug("The index is " + index);
		return index >= 0;
	}
	
}
