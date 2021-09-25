package com.example.littlestep.popwindow

import android.content.Context
import android.widget.TextView
import com.example.littlestep.R
import com.lxj.xpopup.core.HorizontalAttachPopupView

/**
 * create on 2021/9/25 21:56
 * Email:923998007@qq.com
 * @author lin HorizontalAttachPopupView
 */
class NineBoxFuncAttachPopup(
    context: Context,
    private var clickListener: OnClickTabListener
) : HorizontalAttachPopupView(context) {

    companion object {
        /**
         * 完成
         */
        const val FINISH = 1

        /**
         * 放弃
         */
        const val DROP = 2

        /**
         * 从放弃重新打开
         */
        const val REOPEN = 3
    }

    override fun getImplLayoutId(): Int {
        return R.layout.pop_nine_box_func
    }

    override fun onCreate() {
        super.onCreate()

        findViewById<TextView>(R.id.tv_reopen).setOnClickListener(OnClickListener {
            clickListener.clickTab(REOPEN)
            this.dismiss()
        })
        findViewById<TextView>(R.id.tv_finish).setOnClickListener(OnClickListener {
            clickListener.clickTab(FINISH)
            this.dismiss()
        })

        findViewById<TextView>(R.id.tv_drop).setOnClickListener(OnClickListener {
            clickListener.clickTab(DROP)
            this.dismiss()
        })
    }

    interface OnClickTabListener {
        fun clickTab(id: Int)
    }
}