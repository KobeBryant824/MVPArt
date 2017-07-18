package com.cxh.mvpart.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class ActivityManager {

    private static ActivityManager instance;
    private Stack<Activity> activityStack; //activity栈

    public static ActivityManager getInstance() {
        if (instance == null) instance = new ActivityManager();
        return instance;
    }

    /**
     * 把一个activity压入栈中
     *
     * @param actvity activity
     */
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) activityStack = new Stack<>();
        activityStack.add(actvity);
    }

    /**
     * 移除一个activity
     *
     * @param activity activity
     */
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return 栈顶的activity
     */
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束指定的Activity
     *
     * @param activity activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls 指定的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有activity
     */
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

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            System.exit(0); //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            android.os.Process.killProcess(android.os.Process.myPid()); //从操作系统中结束掉当前程序的进程
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}