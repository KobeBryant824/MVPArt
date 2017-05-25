# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# 忽略警告
-ignorewarnings

# 不混淆第三方库，而第三方库有的已经混淆过了，有的使用了Java反射技术，再者第三方不都开源吗索性全不混淆
-keep class android.support.v4.**{*;}
-keep class android.support.v7.**{*;}
-keep class android.support.design.**{*;}

-keep class com.zhy.autolayout.widget.**{*;}
-keep class com.zhy.autolayout.**{*;}
-keep class com.hss01248.pagestate.**{*;}

-keep class butterknife.**{*;}

-keep class com.socks.**{*;}
-keep class com.alibaba.fastjson.**{*;}
-keep class org.greenrobot.eventbus.**{*;}
-keep class com.bumptech.glide.**{*;}
-keep class com.github.**{*;}

-keep class com.jakewharton.rxbinding2.**{*;}
-keep class com.jakewharton.rxbinding2.support.v4.**{*;}
-keep class com.jakewharton.rxbinding2.support.v7.**{*;}
-keep class com.jakewharton.rxbinding2.support.design.**{*;}

-keep class io.reactivex.**{*;}
-keep class io.reactivex.android.**{*;}

-keep class retrofit2.**{*;}
-keep class retrofit2.converter.gson.**{*;}
-keep class retrofit2.converter.scalars.**{*;}
-keep class retrofit2.adapter.rxjava2.**{*;}
-keep class retrofit2.converter.fastjson.**{*;}
-keep class okhttp3.logging.**{*;}

-keep class permissions.dispatcher.**{*;}

-keep class com.trello.rxlifecycle2.android.**{*;}
-keep class com.trello.rxlifecycle2.components.**{*;}

-keep class com.squareup.leakcanary.**{*;}


-dontwarn