package com.cxh.mvpsample.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/8 16:38.
 */
public class RetrofitUtils {
    private static final String BASEURL = "http://www.51el.net/emsap/";
    private static volatile RetrofitUtils sInstance;
    private Retrofit mRetrofit;

    private RetrofitUtils(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(OkHttpUtils.getInstance().getOkHttpClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtils getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitUtils();
                }
            }
        }
        return sInstance;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
