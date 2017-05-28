package br.com.kaiservog.cript.qrcode;

import java.io.ByteArrayOutputStream;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

public class QRGenerator {
	public ByteArrayOutputStream generateJpg(String value) {
		return QRCode.from(value).to(ImageType.JPG).stream();
	}
}
