package com.cxh.mvpart.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class IOUtils {
	
	public static String input2Str(InputStream in) throws IOException {
		return input2Str(in, "utf-8");
	}
	
	public static String input2Str(InputStream in, String encoding) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] bys = new byte[1024];
		int len ;
		while ((len = in.read(bys))!= -1) {
			out.write(bys, 0, len);
		}
		byte[] bytes = out.toByteArray();
		return new String(bytes, encoding);
	}
	
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
