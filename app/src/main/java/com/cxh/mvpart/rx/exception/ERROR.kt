package com.cxh.mvpart.rx.exception

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2016/5/31
 */
object ERROR {
    //未知错误
    const val UNKNOWN = 1000

    //解析错误
    const val PARSE_ERROR = 1001

    //连接错误
    const val CONNECT_ERROR = 1002

    //超时错误
    const val TIMEOUT_ERROR = 1003

    //协议出错
    const val HTTP_ERROR = 1004

    //onNext出错
    const val ONNEXT_ERROR = 1005
}
