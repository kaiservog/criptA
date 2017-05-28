package br.com.kaiservog.cript;

import java.io.UnsupportedEncodingException;

import org.postgresql.util.Base64;

public class KaiserPadding {

	public byte[] padding(byte[] data, int blockSize) {
		int needs = 0;

		if (data.length < blockSize) {
			needs = blockSize - data.length;
		} else {
			for (needs = 0; needs < data.length; needs += 8)
				;
			needs = needs - data.length;
		}

		if (needs == 0)
			return data;

		byte[] paddedData = new byte[data.length + needs];

		for (int i = 0; i < data.length; i++) {
			paddedData[i] = data[i];
		}

		for (int i = 0; i < needs; i++) {
			paddedData[data.length + i] = "Z".getBytes()[0];
		}

		return paddedData;
	}

	public String removePadding(String data) {
		return data.replaceAll("Z*$", "");
	}
}
