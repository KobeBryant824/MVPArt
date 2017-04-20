package com.cxh.mvpsample.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxh.mvpsample.R;


public class ToastUtils {

	/** 安全的显示系统Toast  */
	public static void showSafeToast(final Activity ctx, final String msg){
		if("main".equals(Thread.currentThread().getName())){ // 判断 当前是否是在主线程
			Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
		}else{
			// 不是主线程 
			ctx.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	/** 显示自定义Toast  */
	public static void showToast(Context context, String msg) {
		Toast toast = new Toast(context);
		View view = View.inflate(context, R.layout.toast, null);
		TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
		tv_message.setText(msg);
		// 设置Toast的位置
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);// Toast.LENGTH_SHORT
		// 显示自定义的View
		toast.setView(view);
		toast.show();
	}

	public static void showToast(Context context, String msg, int time) {
		Toast toast = new Toast(context.getApplicationContext());
		View view = View.inflate(context, R.layout.toast, null);
		TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
		tv_message.setText(msg);
		// 设置Toast的位置
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(time);// Toast.LENGTH_SHORT
		// 显示自定义的View
		toast.setView(view);
		toast.show();
	}

	/**
	 * 根据资源ID进行吐司提示
	 *
	 * @param context
	 * @param id
	 */
	public static void showToast(Activity context, int id) {
		if (context == null)
			return;
		String msg = context.getResources().getString(id);
		showToast(context, msg);
	}

	/**
	 * 根据资源ID进行吐司提示
	 *
	 * @param context
	 * @param id
	 */
	public static void showToast(Context context, int id) {
		if (context == null)
			return;
		String msg = context.getResources().getString(id);
		showToast(context, msg);
	}

	public static void showShortToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static void showShortToast(Context context, int message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(Context context, int message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}