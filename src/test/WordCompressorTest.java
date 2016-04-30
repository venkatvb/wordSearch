package test;

import junit.framework.TestCase;

import compressor.WordCompressor;

public class WordCompressorTest extends TestCase {
	
	public void testAddString() {
		WordCompressor wc = new WordCompressor();
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
		WordCompressor wc = new WordCompressor();
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
