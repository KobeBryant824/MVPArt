package com.cxh.mvpart.ui.user

import com.cxh.mvpart.RestfulApi
import com.cxh.mvpart.rx.RxFragmentObserver
import com.cxh.mvpart.rx.RxScheduler
import com.cxh.mvpart.rx.function.HttpResultFunc

import javax.inject.Inject

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
class UserPresenter @Inject constructor(private val mUserActivity: UserActivity, private val mRestfulApi: RestfulApi) : UserContract.Presenter {

    private var mUserFragment: UserContract.View? = null

    override fun takeView(view: UserContract.View) {
        mUserFragment = view

        loadData()
    }

    override fun dropView() {
        mUserFragment = null
    }

    private fun loadData() {
        mRestfulApi
                .getStargazers()
                .onErrorResumeNext(HttpResultFunc())
                .compose(RxScheduler.switchSchedulers(mUserActivity))
                .subscribe(object : RxFragmentObserver<String, UserFragment>() {
                    override fun refreshUI(t: String) {
                        mUserActivity.showContentView()
                        mUserFragment?.setData(t)
                    }
                })
    }
}
