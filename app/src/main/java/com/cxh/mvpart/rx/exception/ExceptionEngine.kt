package com.cxh.mvpart.rx.exception

import com.socks.library.KLog

import org.json.JSONException

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

import retrofit2.HttpException

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2016/5/30
 */
object ExceptionEngine {

    //对应HTTP的状态码
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    fun handleException(e: Throwable): ApiException {
        val ex: ApiException
        if (e is HttpException) {             //HTTP错误
            ex = ApiException(e, ERROR.HTTP_ERROR)
            KLog.e(e.code())
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.displayMessage = "网络错误"
                else -> ex.displayMessage = "网络错误"
            }
            return ex
        } else if (e is ServerException) {    //服务器返回的错误
            ex = ApiException(e, e.code)
            ex.displayMessage = e.msg
            return ex
        } else if (e is com.alibaba.fastjson.JSONException
                || e is JSONException
                || e is ParseException) {
            ex = ApiException(e, ERROR.PARSE_ERROR)
            ex.displayMessage = "解析错误"
            return ex
        } else if (e is ConnectException) {
            ex = ApiException(e, ERROR.CONNECT_ERROR)
            ex.displayMessage = "连接失败"
            return ex
        } else if (e is SocketTimeoutException) {
            ex = ApiException(e, ERROR.TIMEOUT_ERROR)
            ex.displayMessage = "请求或响应超时"
            return ex
        } else {
            ex = ApiException(e, ERROR.UNKNOWN)
            ex.displayMessage = "未知错误"
            return ex
        }
    }
}
