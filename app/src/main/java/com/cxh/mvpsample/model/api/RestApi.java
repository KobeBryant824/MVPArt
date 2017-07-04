package com.cxh.mvpsample.model.api;

import com.cxh.mvpsample.model.entity.WelcomeEntity;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/7
 */
public interface RestApi {

    // BUG  服务器都用request来解析参数
    @POST("version/detail")
    Observable<WelcomeEntity> getUser();


}
