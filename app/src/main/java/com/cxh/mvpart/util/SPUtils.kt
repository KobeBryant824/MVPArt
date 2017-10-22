package com.cxh.mvpart.util

import android.content.Context
import android.content.SharedPreferences

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
object SPUtils {
    val FILE_NAME = "share_data"

    fun put(context: Context, key: String, obj: Any) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (obj) {
            is String -> editor.putString(key, obj)
            is Int -> editor.putInt(key, obj)
            is Boolean -> editor.putBoolean(key, obj)
            is Float -> editor.putFloat(key, obj)
            is Long -> editor.putLong(key, obj)
            else -> editor.putString(key, obj.toString())
        }
        SharedPreferencesCompat.apply(editor)
    }

    operator fun get(context: Context, key: String, defaultObject: Any): Any? {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return when (defaultObject) {
            is String -> sp.getString(key, defaultObject)
            is Int -> sp.getInt(key, defaultObject)
            is Boolean -> sp.getBoolean(key, defaultObject)
            is Float -> sp.getFloat(key, defaultObject)
            is Long -> sp.getLong(key, defaultObject)
            else -> null
        }
    }

    fun remove(context: Context, key: String) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        SharedPreferencesCompat.apply(editor)
    }

    fun clear(context: Context) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        SharedPreferencesCompat.apply(editor)
    }

    fun contains(context: Context, key: String): Boolean {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sp.contains(key)
    }

    fun getAll(context: Context): Map<String, *> {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sp.all
    }

    /** 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类  */
    private object SharedPreferencesCompat {
        private val sApplyMethod = findApplyMethod()

        /** 反射查找apply的方法  */
        private fun findApplyMethod(): Method? {
            try {
                val clz = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
            }

            return null
        }

        /** 如果找到则使用apply执行，否则使用commit  */
        fun apply(editor: SharedPreferences.Editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return
                }
            } catch (e: IllegalArgumentException) {
            } catch (e: IllegalAccessException) {
            } catch (e: InvocationTargetException) {
            }

            editor.commit()
        }
    }
}
