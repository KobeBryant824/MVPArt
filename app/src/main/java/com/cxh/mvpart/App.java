package com.cxh.mvpart;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;

import com.cxh.mvpart.di.DaggerAppComponent;
import com.cxh.mvpart.util.FileUtils;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.greenrobot.eventbus.EventBus;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindString;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class App extends DaggerApplication implements Thread.UncaughtExceptionHandler {

    @BindString(R.string.app_name)
    String mAppName;

    private static App mInstance;

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

        KLog.init(Constant.BUILD, "mtcispdoctor");

        if (!checkDeviceHasNavigationBar(this))
            AutoLayoutConifg.getInstance().useDeviceSize();//拿设备的物理高度(状态栏+导航栏)进行百分比化

        EventBus.builder()
                .throwSubscriberException(Constant.BUILD)//只有在BUILD模式下，会抛出错误异常
                .installDefaultEventBus();

        if (!Constant.BUILD) Thread.currentThread().setUncaughtExceptionHandler(this);

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
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

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }
}
