package com.cxh.mvpart.ui.widget

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.PopupWindow

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
class CustomPopupWindow private constructor(builder: Builder) : PopupWindow() {
    private val mContentView: View?
    private val mParentView: View?
    private val mListener: CustomPopupWindowListener?
    private val isOutsideTouch: Boolean
    private val isFocus: Boolean
    private val mBackgroundDrawable: Drawable
    private val mAnimationStyle: Int
    private val isWrap: Boolean

    init {
        mContentView = builder.contentView
        mParentView = builder.parentView
        mListener = builder.listener
        isOutsideTouch = builder.isOutsideTouch
        isFocus = builder.isFocus
        mBackgroundDrawable = builder.backgroundDrawable
        mAnimationStyle = builder.animationStyle
        isWrap = builder.isWrap
        initLayout()
    }

    private fun initLayout() {
        mListener!!.initPopupView(mContentView)
        width = if (isWrap) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT
        height = if (isWrap) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT
        isFocusable = isFocus
        isOutsideTouchable = isOutsideTouch
        setBackgroundDrawable(mBackgroundDrawable)
        if (mAnimationStyle != -1)
        //如果设置了动画则使用动画
            animationStyle = mAnimationStyle
        contentView = mContentView
    }

    /**
     * 获得用于展示popup内容的view
     *
     * @return
     */
    override fun getContentView(): View? {
        return mContentView
    }

    fun show() {//默认显示到中间
        if (mParentView == null) {
            showAtLocation(mContentView, Gravity.CENTER or Gravity.CENTER_HORIZONTAL, 0, 0)
        } else {
            showAtLocation(mParentView, Gravity.CENTER or Gravity.CENTER_HORIZONTAL, 0, 0)
        }
    }

    class Builder {
        var contentView: View? = null
        var parentView: View? = null
        var listener: CustomPopupWindowListener? = null
        var isOutsideTouch = true//默认为true
        var isFocus = true//默认为true
        var backgroundDrawable: Drawable = ColorDrawable(0x00000000)//默认为透明
        var animationStyle = -1
        var isWrap: Boolean = false

        fun contentView(contentView: View): Builder {
            this.contentView = contentView
            return this
        }

        fun parentView(parentView: View): Builder {
            this.parentView = parentView
            return this
        }

        fun isWrap(isWrap: Boolean): Builder {
            this.isWrap = isWrap
            return this
        }


        fun customListener(listener: CustomPopupWindowListener): Builder {
            this.listener = listener
            return this
        }

        fun isOutsideTouch(isOutsideTouch: Boolean): Builder {
            this.isOutsideTouch = isOutsideTouch
            return this
        }

        fun isFocus(isFocus: Boolean): Builder {
            this.isFocus = isFocus
            return this
        }

        fun backgroundDrawable(backgroundDrawable: Drawable): Builder {
            this.backgroundDrawable = backgroundDrawable
            return this
        }

        fun animationStyle(animationStyle: Int): Builder {
            this.animationStyle = animationStyle
            return this
        }

        fun build(): CustomPopupWindow {
            if (contentView == null)
                throw IllegalStateException("contentView is required")
            if (listener == null)
                throw IllegalStateException("CustomPopupWindowListener is required")

            return CustomPopupWindow(this)
        }
    }

    interface CustomPopupWindowListener {
        fun initPopupView(contentView: View?)
    }

    companion object {

        fun builder(): Builder {
            return Builder()
        }

        /**
         * 用于填充contentView,必须传ContextThemeWrapper(比如activity)不然popupwindow要报错
         *
         * @param context
         * @param layoutId
         * @return
         */
        fun inflateView(context: ContextThemeWrapper, layoutId: Int): View {
            return LayoutInflater.from(context).inflate(layoutId, null)
        }
    }

}
