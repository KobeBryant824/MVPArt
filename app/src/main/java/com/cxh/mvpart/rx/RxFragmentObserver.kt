package com.cxh.mvpart.rx

import com.cxh.mvpart.Constant
import com.cxh.mvpart.base.BaseFragment
import com.cxh.mvpart.rx.exception.ApiException
import com.cxh.mvpart.rx.exception.ERROR
import com.cxh.mvpart.util.ToastUtils

import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/31
 */
abstract class RxFragmentObserver<T, K : BaseFragment> : Observer<T> {
    private var k: K? = null

    constructor()

    protected constructor(k: K) {
        this.k = k
    }

    override fun onSubscribe(@NonNull d: Disposable) {

    }

    override fun onNext(@NonNull t: T) {
        k?.showContentView()
        refreshUI(t)
    }

    override fun onError(@NonNull e: Throwable) {
        k?.showErrorView()

        if (e is ApiException) {
            onError(e)
        } else {
            val apiException = ApiException(e, ERROR.ONNEXT_ERROR)
            apiException.displayMessage = "onNext()出错"
            onError(apiException)
        }
    }

    override fun onComplete() {

    }

    private fun onError(ex: ApiException) {
        if (Constant.BUILD) {
            if (ex.code == ERROR.ONNEXT_ERROR)
                throw RuntimeException(ex.cause)
            else
                ToastUtils.show(ex.displayMessage)
        } else {
            ToastUtils.show(ex.displayMessage)
        }
    }

    protected abstract fun refreshUI(t: T)

}


