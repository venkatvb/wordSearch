package searcher;

import java.util.Random;

import junit.framework.TestCase;

public class WordContainerTest extends TestCase {
	
	public void testAddString() {
		WordContainer wc = new WordContainer();
		boolean result;
		result = wc.addString("venkat");
		assertEquals(result, true);
		result = wc.addString("venkat");
		assertEquals(result, true);
		result = wc.addString("vb");
		assertEquals(result, true);
		result = wc.addString("Vb");
		assertEquals(result, false);
		result = wc.addString("lenghtistwel");
		assertEquals(result, true);
		result = wc.addString("lengthisgreaterthantwelve");
		assertEquals(result, false);
		result = wc.addString("    vb    ");
		assertEquals(result, true);
		result = wc.addString("123");
		assertEquals(result, false);
		result = wc.addString("ven@");
		assertEquals(result, false);
		result = wc.addString("                               vb                  ");
		assertEquals(result, true);
		result = wc.addString("v b");
		assertEquals(result, false);
		
	}
	
	public void testCount() {
		WordContainer wc = new WordContainer();
		int count;
		count = wc.count();
		assertEquals(count, 0);
		wc.addString("venkat");
		count = wc.count();
		assertEquals(count, 1);
		wc.addString("vb");
		count = wc.count();
		assertEquals(count, 2);
		wc.addString("venkat");
		count = wc.count();
		assertEquals(count, 3);
	}
	
}
