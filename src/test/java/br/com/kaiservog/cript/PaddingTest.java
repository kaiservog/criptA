package br.com.kaiservog.cript;

import org.junit.Test;

import junit.framework.Assert;

public class PaddingTest {

	
	@Test
	public void testPaddingLesserThanBlockSize() {
		KaiserPadding padding = new KaiserPadding();
		
		byte[] padded = padding.padding("12345".getBytes(), 8);
		Assert.assertEquals(8, padded.length);
	}
	
	@Test
	public void testPaddingBiggerThanBlockSize() {
		KaiserPadding padding = new KaiserPadding();
		
		byte[] padded = padding.padding("123456789".getBytes(), 8);
		Assert.assertEquals(16, padded.length);
	}
	
	@Test
	public void testPaddingEqualsThanBlockSize() {
		KaiserPadding padding = new KaiserPadding();
		
		byte[] padded = padding.padding("12345678".getBytes(), 8);
		Assert.assertEquals(8, padded.length);
	}
	
	@Test
	public void testPaddingTwiceThanBlockSize() {
		KaiserPadding padding = new KaiserPadding();
		
		byte[] padded = padding.padding("1234567890123456".getBytes(), 8);
		Assert.assertEquals(16, padded.length);
	}
}
