package com.cxh.mvpart.manager

import android.app.Activity

import java.util.Stack

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
object ActivityManager {
    private var activityStack: Stack<Activity>? = null

    fun pushOneActivity(actvity: Activity) {
        if (activityStack == null) activityStack = Stack()
        activityStack?.add(actvity)
    }

    fun popOneActivity(activity: Activity?) {
        if (activityStack != null && activityStack!!.size > 0) {
            if (activity != null) {
                activityStack!!.remove(activity)
                activity.finish()
            }
        }
    }

    fun getLastActivity(): Activity? {
        return activityStack?.lastElement()
    }

    fun finishActivity(activity: Activity?) {
        activity?.let {
            activityStack?.remove(activity)
            activity.finish()
        }
    }

    fun finishActivity(cls: Class<*>) {
        activityStack?.filter { it.javaClass == cls }?.forEach { finishActivity(it) }
    }

    fun finishAllActivity() {
        try {
            for (i in activityStack!!.indices) {
                if (null != activityStack!![i]) {
                    activityStack!![i].finish()
                }
            }
            activityStack?.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun appExit() {
        try {
            finishAllActivity()
            System.exit(0) //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}