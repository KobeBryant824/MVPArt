package com.cxh.mvpart.ui.widget.autolayout

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.ViewGroup

import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

class AutoToolbar : Toolbar {
    private val mHelper = AutoLayoutHelper(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode)
            mHelper.adjustChildren()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return AutoToolbar.LayoutParams(context, attrs)
    }

    class LayoutParams : Toolbar.LayoutParams, AutoLayoutHelper.AutoLayoutParams {
        private lateinit var mAutoLayoutInfo: AutoLayoutInfo

        constructor(c: Context, attrs: AttributeSet) : super(c, attrs) {
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs)
        }

        override fun getAutoLayoutInfo(): AutoLayoutInfo {
            return mAutoLayoutInfo
        }

        constructor(width: Int, height: Int) : super(width, height)

        constructor(source: ViewGroup.LayoutParams) : super(source)

        constructor(source: ViewGroup.MarginLayoutParams) : super(source)

    }
}
