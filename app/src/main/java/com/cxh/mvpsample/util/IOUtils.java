package com.cxh.mvpsample.util;

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
		int len = 0;
		while ((len = in.read(bys))!= -1) {
			out.write(bys, 0, len);
		}
		byte[] bytes = out.toByteArray();
		String str = new String(bytes, encoding);
		return str;
	}
	
	/** 关闭流 */
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
