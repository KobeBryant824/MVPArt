package com.cxh.mvpart.rx.exception;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2016/5/31
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
