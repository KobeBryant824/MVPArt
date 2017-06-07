package com.cxh.mvpsample.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.cxh.mvpsample.Constants.BASEURL;

/**
 * Retrofit构造器
 * Created by Hai (haigod7@gmail.com) on 2017/3/8 14:07.
 */
public class RetrofitProvider {
    private static volatile RetrofitProvider sInstance;
    private Retrofit mRetrofit;

    private RetrofitProvider(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(OkHttpClientProvider.getInstance().getOkHttpClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitProvider getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitProvider.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitProvider();
                }
            }
        }
        return sInstance;
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
