package com.cxh.mvpart.util

import android.support.annotation.StringRes
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.cxh.mvpart.App
import com.cxh.mvpart.R

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
object ToastUtils {

    fun show(message: String?) {
        message?.let {
            Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun show(@StringRes message: Int) {
        Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
    }

    fun showCustom(msg: String) {
        val toast = Toast(App.instance)
        val view = View.inflate(App.instance, R.layout.toast, null)
        val tv_message = view.findViewById<TextView>(R.id.tv_message)
        tv_message.text = msg
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = view
        toast.show()
    }

}