package com.cxh.mvpart.util;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxh.mvpart.App;
import com.cxh.mvpart.R;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class ToastUtils {

    public static void show(String message) {
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int message) {
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showCustom(String msg) {
        Toast toast = new Toast(App.getInstance());
        View view = View.inflate(App.getInstance(), R.layout.toast, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_message.setText(msg);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}