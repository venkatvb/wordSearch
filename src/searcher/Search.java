package searcher;

public interface Search {
	public boolean isPresent(String s);
	public boolean addString(String s);
	public int count();
}
