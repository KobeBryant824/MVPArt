package com.cxh.mvpsample;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;

import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindString;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class MApplication extends Application implements Thread.UncaughtExceptionHandler {
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static MApplication mAppContext;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    private List<Activity> mActivityList = new LinkedList<>();

    @BindString(R.string.app_name)
    String mAppName;

    public static MApplication getContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        // android.os.Process.myTid() 获取调用进程的id
        // android.os.Process.myUid() 获取该进程的用户id
        // android.os.Process.myPid() 获取进程的id
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mAppContext = this;

        super.onCreate();

        KLog.init(BuildConfig.DEBUG, mAppName);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        /**
         * 给当前线程，设置一个，全局异常捕获
         * 说明：线程中，没有try catch的地方，抛了异常，都由该方法捕获，上线请打开
         */
//		Thread.currentThread().setUncaughtExceptionHandler(this);

    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public void addPage(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * 退出app，清理没关闭的页面，杀死进程，手动释放内存
     */
    public void exitApp() {
        if (mActivityList.size() == 0) return;

        // 链表使用foreach 数组使用for 更快
        for (Activity activity : mActivityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 当应用崩溃的时候，捕获异常
     * 1、该用应程序，在此处，必死无异，不能原地复活，只能，留个遗言，即，记录一下，崩溃的log日志，以便开发人员处理
     * 2、将自己彻底杀死，早死早超生。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        try {
            PrintStream printStream = new PrintStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/error.log");

            Class clazz = Class.forName("android.os.Build");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                printStream.println(field.getName() + " : " + field.get(null));
            }
            String currTime = DateFormat.getDateFormat(getApplicationContext()).format(System.currentTimeMillis());

            printStream.println("TIME:" + currTime);
            printStream.println("==================华丽丽的分隔线================");
            ex.printStackTrace(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2、将自己彻底杀死
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
