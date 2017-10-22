package com.cxh.mvpart.util

import android.os.Environment
import android.os.StatFs

import java.io.File

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
object SDCardUtils {

    fun isSDCardEnable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun getSDCardPath(): String {
        return Environment.getExternalStorageDirectory().absolutePath + File.separator
    }

    fun getSDCardAllSize(): Long {
        if (isSDCardEnable()) {
            val stat = StatFs(getSDCardPath())
            val availableBlocks = stat.availableBlocks.toLong() - 4
            val freeBlocks = stat.availableBlocks.toLong()
            return freeBlocks * availableBlocks
        }
        return 0
    }

    fun getFreeBytes(filePath: String): Long {
        var filePath = filePath
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath()
        } else {
            filePath = Environment.getDataDirectory().absolutePath
        }
        val stat = StatFs(filePath)
        val availableBlocks = stat.availableBlocks.toLong() - 4
        return stat.blockSize * availableBlocks
    }

    fun getRootDirectoryPath(): String {
        return Environment.getRootDirectory().absolutePath
    }
}
