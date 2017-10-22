package com.cxh.mvpart.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class ActivityManager {

    private static ActivityManager INSTANCE;
    private Stack<Activity> activityStack;

    public static ActivityManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ActivityManager();
        return INSTANCE;
    }

    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) activityStack = new Stack<>();
        activityStack.add(actvity);
    }

    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        try {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appExit() {
        try {
            finishAllActivity();
            System.exit(0); //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}