package com.cxh.mvpsample.model.api;

import com.cxh.mvpsample.model.entity.WelcomeEntity;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 请求Url，根据模块来设计api
 * Created by Hai (haigod7@gmail.com) on 2017/3/7 17:19.
 */
public interface RequestApi {

    // post请求无参
    @POST("version/detail")
    Call<WelcomeEntity> post0();

    // BUG  服务器都用request来解析参数
    @FormUrlEncoded
    @POST("uuc/login.do")
//    Call<String> post2(@Field("subject") String subject, @Field("password") String password);
    Call<String> post2(@FieldMap Map<String, String> map);

    // get请求
    @GET()
    Call<String> get(@Url String url, @Query("type") String type, @Query("postid") String postid);

    //post请求结合rxjava
    @POST("version/detail")
    Observable<WelcomeEntity> getDataByRxjava();

    //post请求结合rxjava
    @POST("version/detail")
    Flowable<WelcomeEntity> getFlowableByRxjava();
}
