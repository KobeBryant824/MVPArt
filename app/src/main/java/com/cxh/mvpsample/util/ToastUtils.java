package com.cxh.mvpsample.util;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.R;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class ToastUtils {

    /**
     * 显示默认Toast
     */
    public static void show(String message) {
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int message) {
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 居中显示自定义Toast
     */
    public static void showCust(String msg) {
        Toast toast = new Toast(App.getInstance());
        View view = View.inflate(App.getInstance(), R.layout.toast, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_message.setText(msg);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * 安全的显示系统Toast
     */
    public static void safeShow(final Activity ctx, final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();

        } else {

            ctx.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}