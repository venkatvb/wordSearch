package compressor;

public interface Compressable {
	public boolean readFromFile(String address);
	public boolean readFromStdin();
	public boolean writeToFile(String address);
	public boolean writeToStdout();
	public boolean addString(String s);
	public int count();
	public void sort();
}
