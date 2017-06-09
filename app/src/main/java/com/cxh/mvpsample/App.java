package com.cxh.mvpsample;

import android.app.Application;
import android.text.format.DateFormat;

import com.cxh.mvpsample.util.FileUtils;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;

import java.io.PrintStream;
import java.lang.reflect.Field;

import butterknife.BindString;
import retrofit2.Retrofit;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class App extends Application implements Thread.UncaughtExceptionHandler {

    @BindString(R.string.app_name)
    String mAppName;

    private static App mInstance;

    private static AppComponent mAppComponent;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        KLog.init(BuildConfig.DEBUG, mAppName);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        /**
         * 给当前线程，设置一个，全局异常捕获
         * 说明：线程中，没有try catch的地方，抛了异常，都由该方法捕获，上线请打开
         */
//		Thread.currentThread().setUncaughtExceptionHandler(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Retrofit getRetrofit(){
        return mAppComponent.getRetrofit();
    }

    /**
     * 当应用崩溃的时候，捕获异常
     * 1、该用应程序，在此处，必死无异，不能原地复活，只能，留个遗言，即，记录一下，崩溃的log日志，以便开发人员处理
     * 2、将自己彻底杀死，早死早超生。
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        try {
            PrintStream printStream = new PrintStream(FileUtils.getDownloadDir() + "error.log");

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
