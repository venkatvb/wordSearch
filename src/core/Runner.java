package core;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import buildConfig.Config;

import searcher.WordContainer;

public class Runner {
	
	private static final boolean DEBUG = Config.DEBUG;
	
	public static void main(String args[]) throws Exception {
		WordContainer words = new WordContainer();
		Scanner s = new Scanner(System.in);
		int numberOfStrings;
		if(DEBUG)
		System.out.println("Enter the number of words");
		// To read from file
		// DataInputStream dis = new DataInputStream(new FileInputStream(Config.INPUT_FILE_LOCATION));
		// To read the contents from the standard output stream
		DataInputStream dis = new DataInputStream(System.in);
		numberOfStrings = Integer.parseInt(dis.readLine());
		for(int i=0; i<numberOfStrings; i++ ) {
			String word = dis.readLine();
			if(words.addString(word)) {
				if(DEBUG)
				System.out.println("Word Added : " + word);
			} else {
				if(DEBUG)
				System.out.println("Failed to add word");
			}
		}
		words.completed();
		ArrayList<Long> w = words.getWords();
		if(DEBUG) {
			System.out.println("Printing the sorted list of words");
			for(Long word : w) {
				String res = words.getStringOfLong(word);
				System.out.println(res);
			}
			System.out.println("Writing the contents to the file");
		}
		try {
			words.serialize();
			if(DEBUG)
			System.out.println("Words are successfully written as the binary format to the file");
		} catch (Exception e) {
			System.out.println("Failed to write the contents of the file");
			e.printStackTrace();
		}
		if(DEBUG)
		System.out.println("Reading the contents of the file in binary format");
		try {
			words.readFromFile(numberOfStrings);
			if(DEBUG)
			System.out.println("Successfully read the binary Long data from file");
		} catch(Exception e) {
			System.out.println("Failed to read the binary data from file");
			e.printStackTrace();
		}
		int qCount= 0;
		if(DEBUG) {
			System.out.println("Enter the numebr of queries");
		}
		qCount = Integer.parseInt(dis.readLine());
		while(true) {
			if(qCount <= 0) {
				break;
			}
			String word = dis.readLine();
			boolean found = words.isPresent(word);
			if(found) {
				System.out.println("The word " + word + " is found");
			} else {
				System.out.println("The workd " + word + " is NOT found");
			}
			qCount -= 1;
		}
	}
}
