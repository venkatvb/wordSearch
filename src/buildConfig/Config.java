package buildConfig;

public class Config {
	public static final boolean DEBUG = true;
	public static void debug(String s) {
		if(DEBUG) {
			System.out.println(s);
		}
	}
}
