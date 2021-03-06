package com.cxh.mvpart.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class SDCardUtils {
	
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	public static long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	public static long getFreeBytes(String filePath) {
		if (filePath.startsWith(getSDCardPath())) {
			filePath = getSDCardPath();
		} else {
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}
}
