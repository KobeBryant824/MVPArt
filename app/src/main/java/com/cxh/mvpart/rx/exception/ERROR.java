package com.cxh.mvpart.rx.exception;


public class ERROR {

    //未知错误
    public static final int UNKNOWN = 1000;

    //解析错误
    public static final int PARSE_ERROR = 1001;

    //连接错误
    public static final int CONNECT_ERROR = 1002;

    //超时错误
    public static final int TIMEOUT_ERROR = 1003;

    //协议出错
    public static final int HTTP_ERROR = 1004;

    //onNext出错
    public static final int ONNEXT_ERROR = 1005;
}
