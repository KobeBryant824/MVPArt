package com.cxh.mvpart

import android.content.Context
import android.text.format.DateFormat
import com.cxh.mvpart.di.DaggerAppComponent
import com.cxh.mvpart.util.FileUtils
import com.socks.library.KLog
import com.squareup.leakcanary.LeakCanary
import com.zhy.autolayout.config.AutoLayoutConifg
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.greenrobot.eventbus.EventBus
import java.io.PrintStream

/**
 * @author A-Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
class App : DaggerApplication(), Thread.UncaughtExceptionHandler {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        instance = this

        KLog.init(Constant.BUILD, getString(R.string.app_name))

        if (!checkDeviceHasNavigationBar(this))
            AutoLayoutConifg.getInstance().useDeviceSize()//拿设备的物理高度(状态栏+导航栏)进行百分比化

        EventBus.builder()
                .throwSubscriberException(Constant.BUILD)//只有在BUILD模式下，会抛出错误异常
                .addIndex(MyEventBusIndex())
                .installDefaultEventBus()

        if (!Constant.BUILD) Thread.currentThread().uncaughtExceptionHandler = this

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {

        try {
            val printStream = PrintStream(FileUtils.getDownloadDir() + "error.log")

            val clazz = Class.forName("android.os.Build")
            val fields = clazz.declaredFields
            for (field in fields) {
                field.isAccessible = true
                printStream.println(field.name + " : " + field.get(null))
            }
            val currTime = DateFormat.getDateFormat(applicationContext).format(System.currentTimeMillis())

            printStream.println("TIME:" + currTime)
            printStream.println("==================华丽丽的分隔线================")
            ex.printStackTrace(printStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        android.os.Process.killProcess(android.os.Process.myPid())
    }

    companion object {

        lateinit var instance: App

        //获取是否存在NavigationBar
        fun checkDeviceHasNavigationBar(context: Context): Boolean {
            var hasNavigationBar = false
            val rs = context.resources
            val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
            if (id > 0) {
                hasNavigationBar = rs.getBoolean(id)
            }
            try {
                val systemPropertiesClass = Class.forName("android.os.SystemProperties")
                val m = systemPropertiesClass.getMethod("get", String::class.java)
                val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
                if ("1" == navBarOverride) {
                    hasNavigationBar = false
                } else if ("0" == navBarOverride) {
                    hasNavigationBar = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return hasNavigationBar
        }
    }
}
