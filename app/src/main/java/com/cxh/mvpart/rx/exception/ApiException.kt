package com.cxh.mvpart.rx.exception

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2016/5/31
 */
class ApiException(throwable: Throwable, val code: Int) : Exception(throwable) {
    var displayMessage: String? = null
}
