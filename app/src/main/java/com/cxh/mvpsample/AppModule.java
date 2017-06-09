package com.cxh.mvpsample;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.cxh.mvpsample.Constants.BASEURL;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7 15:43
 */
@Module
public class AppModule {

    public static final long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000;
    public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;

    private App mApplication;

    public AppModule(App application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public App provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(provideOkHttpClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(FastJsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(KLog::e);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); //包含header、body数据

        return new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
//                .addNetworkInterceptor(new StethoInterceptor())
                //http数据log，日志中打印出HTTP请求&响应数据
                .addInterceptor(loggingInterceptor)
                .build();
    }

}
