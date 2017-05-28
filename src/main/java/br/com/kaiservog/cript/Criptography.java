package br.com.kaiservog.cript;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Criptography {

	public static final String ENCODING = "UTF-8";
	public static final int BLOCK_SIZE = 8;

	public String cript(String value, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] data = new KaiserPadding().padding(value.getBytes(ENCODING), BLOCK_SIZE);

		Cipher cipher = getCipher();
		cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
		byte[] textEncrypted = cipher.doFinal(data);

		return Base64.getEncoder().encodeToString(textEncrypted);
	}

	public String uncript(String value, String key) throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		
		Cipher cipher = getCipher();
		cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

		byte[] textDecrypted = cipher.doFinal(Base64.getDecoder().decode(value));
		String stringDecrypted = new String(textDecrypted, ENCODING);

		stringDecrypted = new KaiserPadding().removePadding(stringDecrypted);
		return stringDecrypted;
	}

	private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("DES/ECB/NoPadding");
	}

	private SecretKey getSecretKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec((key + "12345678").substring(0, 8).getBytes("UTF-8"), "DES");
		return secretKeySpec;
	}

}
