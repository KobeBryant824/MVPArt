package com.cxh.mvpart;

import android.app.Application;
import android.text.format.DateFormat;

import com.cxh.mvpart.di.component.AppComponent;
import com.cxh.mvpart.di.component.DaggerAppComponent;
import com.cxh.mvpart.di.moduel.AppModule;
import com.cxh.mvpart.model.repository.RxCacheClient;
import com.cxh.mvpart.util.FileUtils;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import java.io.PrintStream;
import java.lang.reflect.Field;

import butterknife.BindString;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        mInstance = this;

        KLog.init(BuildConfig.DEBUG, mAppName);

        EventBus.builder()
                .throwSubscriberException(BuildConfig.DEBUG)//只有在debug模式下，会抛出错误异常
                .installDefaultEventBus();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

//		Thread.currentThread().setUncaughtExceptionHandler(this); 上线打开
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static RxCacheClient getRxCacheClient(){
        return mAppComponent.getRxCacheClient();
    }

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

        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
