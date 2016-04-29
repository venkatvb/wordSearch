package buildConfig;

public class Config {
	public static final boolean DEBUG = false;
	public static final String DATA_FILE_LOCATION = "/home/venkatvb/project/WordSearch/output/result.dat";
	public static final String INPUT_FILE_LOCATION = "/home/venkatvb/project/WordSearch/input/input.txt";
	public static void debug(String s) {
		if(DEBUG) {
			System.out.println(s);
		}
	}
}
