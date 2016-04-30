package compressor;

import helper.StringHelper;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import buildConfig.Config;

public class WordCompressor implements Compressable {	
	
	public static String CLASS_NAME = Compressable.class.getSimpleName();
	private ArrayList<Long> words;
	
	public WordCompressor() {
		words = new ArrayList<Long>();
	}
	
	private boolean readFromScanner(Scanner sc) {
		int count = sc.nextInt();
		for(int i=0; i<count; i++ ) {
			String s = sc.next();
			addString(s);
		}
		return true;
	}
	
	private boolean writeToDataOutputStream(DataOutputStream dos) {
		try {
			for(Long value : words) {
				dos.writeLong(value);
			}
			dos.close();
		} catch(Exception e) {
			Config.debug("Exception at writeToDataOutputStream " + CLASS_NAME + e);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean readFromFile(String address) {
		try {
			Scanner sc = new Scanner(new FileInputStream(address));
			return readFromScanner(sc);
		} catch(Exception e) {
			Config.debug("Exception at readFromFile : " + CLASS_NAME + e);
		}
		return true;
	}

	@Override
	public boolean readFromStdin() {
		try {
			Scanner sc = new Scanner(System.in);
			return readFromScanner(sc);
		} catch(Exception e) {
			Config.debug("Exception at readFromStdin : " + CLASS_NAME + e);
		}
		return false;
	}

	@Override
	public boolean writeToFile(String address) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(Config.DATA_FILE_LOCATION));
			writeToDataOutputStream(dos);
		} catch(Exception e) {
			Config.debug("Exception in writeToFile : " + CLASS_NAME + e);
		}
		return false;
	}

	@Override
	public boolean writeToStdout() {
		DataOutputStream dos = new DataOutputStream(System.out);
		return writeToDataOutputStream(dos);
	}
	
	@Override
	public boolean addString(String input) {
		input = input.trim();
		if(!StringHelper.validateString(input)) {
			return false;
		}
		Long value = StringHelper.getLongOfString(input);
		words.add(value);
		Config.debug(value.toString());
		return true;
	}
	
	@Override
	public int count() {
		return this.words.size();
	}
	
	@Override
	public void sort() {
		Collections.sort(words);
	}
}
