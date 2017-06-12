package com.cxh.mvpsample;

import android.app.Application;
import android.text.format.DateFormat;

import com.cxh.mvpsample.di.component.AppComponent;
import com.cxh.mvpsample.di.component.DaggerAppComponent;
import com.cxh.mvpsample.di.moduel.AppModule;
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

//		Thread.currentThread().setUncaughtExceptionHandler(this); 上线打开

        LeakCanary.install(this);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Retrofit getRetrofit(){
        return mAppComponent.getRetrofit();
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
