package br.com.kaiservog.cript;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import junit.framework.Assert;

public class CriptTest {

	@Test
	public void testCript() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException,
			IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
		Criptography c = new Criptography();
		String secret = new String("huehue");
		String secretKey = "12345678";
		
		String trash = c.cript(secret, secretKey);
		String real = c.uncript(trash, secretKey);
		
		Assert.assertEquals(secret, new String(real));

	}
	
	@Test 
	public void assertSecurityPatter() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Criptography c = new Criptography();
		String secret = new String("hello");
		String secretKey = "12345678";
		
		String trash = c.cript(secret, secretKey);
		
		Assert.assertEquals(trash, "RzCGJ71KDcg=");
	}

	private void showHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X ", b));
		}
		System.out.println(sb.toString());
	}
}
