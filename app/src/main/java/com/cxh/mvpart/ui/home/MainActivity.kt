package com.cxh.mvpart.ui.home

import android.widget.Toast
import com.cxh.mvpart.R
import com.cxh.mvpart.base.BaseActivity
import com.cxh.mvpart.manager.ActivityManager
import com.cxh.mvpart.ui.user.UserActivity
import com.cxh.mvpart.ui.widget.ClearEditText
import com.cxh.mvpart.ui.widget.CustomPopupWindow
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
class MainActivity : BaseActivity() {

    private var mDoubleBackToExitPressedOnce = false

    override fun isInject() = false

    override fun displayHomeAsUpEnabled() = false

    override fun getLayoutID() = R.layout.activity_main

    override fun refreshState() {

    }

    override fun initViewsAndEvents() {
        toolbarTitle.setText(R.string.app_name)
        mvpBtn.onClick {
            startActivity<UserActivity>()
        }
    }

    override fun onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            super.onBackPressed()
            ActivityManager.appExit()
            return
        }
        mDoubleBackToExitPressedOnce = true
        Toast.makeText(this, "再次点击退出MVPArt", Toast.LENGTH_SHORT).show()

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe { mDoubleBackToExitPressedOnce = false }

    }

}
