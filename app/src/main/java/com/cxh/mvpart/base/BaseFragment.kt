package com.cxh.mvpart.base


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cxh.mvpart.R
import com.fingdo.statelayout.StateLayout
import com.socks.library.KLog
import com.trello.rxlifecycle2.components.support.RxFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/21
 */
abstract class BaseFragment : RxFragment(), AnkoLogger, StateLayout.OnViewRefreshListener, HasSupportFragmentInjector {

    private var stateLayout: StateLayout? = null

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        if (isInject()) AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, getLayoutID(), null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isUseEventBus()) EventBus.getDefault().register(this)

        setupStateLayout(view)

        initViewsAndEvents()
    }

    private fun setupStateLayout(view: View?) {
        stateLayout = view?.findViewById(R.id.stateLayout)
        stateLayout?.let {
            stateLayout?.isUseAnimation = false
            stateLayout?.setTipText(5, getString(R.string.statelayout_loading))
            stateLayout?.setRefreshListener(this)
            stateLayout?.showLoadingView()
        }
    }

    fun showLoginView() {
        stateLayout?.showLoginView()
    }

    fun showContentView() {
        stateLayout?.showContentView()
    }

    fun showErrorView() {
        stateLayout?.showErrorView()
    }

    fun showTimeoutView() {
        stateLayout?.showTimeoutView()
    }

    override fun refreshClick() {
        stateLayout?.showLoadingView()
        refreshState()
    }

    override fun loginClick() {}

    @Subscribe
    fun onEvent(event: String) {
        KLog.e(event)
    }

    override fun onDestroyView() {
        if (isUseEventBus()) EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }

    protected open fun isInject() = true

    protected open fun isUseEventBus() = false

    protected abstract fun getLayoutID(): Int

    protected abstract fun initViewsAndEvents()

    protected abstract fun refreshState()

}
