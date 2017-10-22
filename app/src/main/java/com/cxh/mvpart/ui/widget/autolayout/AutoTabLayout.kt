package com.cxh.mvpart.ui.widget.autolayout

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import com.cxh.mvpart.R
import com.zhy.autolayout.utils.AutoUtils
import com.zhy.autolayout.utils.DimenUtils

class AutoTabLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TabLayout(context, attrs, defStyleAttr) {
    private val mTextSize: Int
    private var mTextSizeBaseWidth = false

    init {

        initTextSizeBaseWidth(context, attrs)

        val a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, R.style.Widget_Design_TabLayout)
        val tabTextAppearance = a.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab)

        mTextSize = loadTextSizeFromTextAppearance(tabTextAppearance)
        a.recycle()
    }

    private fun initTextSizeBaseWidth(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AutoTabLayout)
        mTextSizeBaseWidth = a.getBoolean(R.styleable.AutoTabLayout_auto_textSize_base_width, false)
        a.recycle()
    }

    private fun loadTextSizeFromTextAppearance(textAppearanceResId: Int): Int {
        val a = context.obtainStyledAttributes(textAppearanceResId, R.styleable.TextAppearance)

        try {
            return if (!DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize))) NO_VALID
            else a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, NO_VALID)
        } finally {
            a.recycle()
        }
    }

    override fun addTab(tab: TabLayout.Tab, position: Int, setSelected: Boolean) {
        super.addTab(tab, position, setSelected)
        setUpTabTextSize(tab)
    }

    override fun addTab(tab: TabLayout.Tab, setSelected: Boolean) {
        super.addTab(tab, setSelected)
        setUpTabTextSize(tab)
    }

    private fun setUpTabTextSize(tab: TabLayout.Tab) {
        if (mTextSize == NO_VALID || tab.customView != null) return

        val tabGroup = getChildAt(0) as ViewGroup
        val tabContainer = tabGroup.getChildAt(tab.position) as ViewGroup
        val textView = tabContainer.getChildAt(1) as TextView

        if (AutoUtils.autoed(textView)) {
            return
        }
        val autoTextSize = if (mTextSizeBaseWidth) {
            AutoUtils.getPercentWidthSize(mTextSize)
        } else {
            AutoUtils.getPercentHeightSize(mTextSize)
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, autoTextSize.toFloat())
    }

    companion object {
        private val NO_VALID = -1
    }

}
