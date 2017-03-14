package com.cxh.mvpsample.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/8 16:38.
 */
public class RetrofitUtil {
    private static final String BASEURL = "http://www.51el.net/emsap/";
    private static volatile RetrofitUtil sInstance;
    private Retrofit mRetrofit;

    private RetrofitUtil(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(OkHttpUtil.getInstance().getOkHttpClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitUtil();
                }
            }
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

}
