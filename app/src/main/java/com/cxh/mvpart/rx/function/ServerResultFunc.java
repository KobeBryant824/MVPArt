package com.cxh.mvpart.rx.function;



import com.cxh.mvpart.base.Response;
import com.cxh.mvpart.rx.exception.ServerException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/30
 */
public class ServerResultFunc<T> implements Function<Response<T>, T> {

    @Override
    public T apply(@NonNull Response<T> httpResponse) throws Exception {
        if (httpResponse.code != 1) {
            throw new ServerException(httpResponse.code, httpResponse.msg);
        }
        return httpResponse.data;
    }
}