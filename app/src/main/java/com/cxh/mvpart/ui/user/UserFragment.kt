package com.cxh.mvpart.ui.user

import com.cxh.mvpart.R
import com.cxh.mvpart.base.BaseFragment
import com.cxh.mvpart.di.ActivityScoped
import com.cxh.mvpart.util.GlideUtils
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
@ActivityScoped
class UserFragment @Inject constructor() : BaseFragment(), UserContract.View {

    @Inject lateinit var mUserPresenter: UserPresenter

    public override fun getLayoutID(): Int {
        return R.layout.fragment_user
    }

    override fun initViewsAndEvents() {
        val path = "https://avatars0.githubusercontent.com/u/13111493?v=4&s=460"
        GlideUtils.loadImage(path, showImage)
    }

    override fun refreshState() {
        mUserPresenter.takeView(this)
    }

    override fun onResume() {
        super.onResume()
        mUserPresenter.takeView(this)
    }

    override fun onPause() {
        mUserPresenter.dropView()
        super.onPause()
    }

    override fun setData(data: Any) {
        startsTextView.text = data as String
    }
}
