package com.cxh.mvpart.ui.user

import com.cxh.mvpart.R
import com.cxh.mvpart.base.BaseActivity
import com.cxh.mvpart.util.ActivityUtils
import javax.inject.Inject

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
class UserActivity : BaseActivity() {

    @Inject lateinit var mUserFragment: UserFragment

    public override fun getLayoutID(): Int {
        return R.layout.activity_user
    }

    override fun refreshState() {

    }

    override fun initViewsAndEvents() {
        ActivityUtils.addFragmentToActivity(supportFragmentManager, mUserFragment, R.id.content)
    }

}
