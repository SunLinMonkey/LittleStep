package com.example.littlestep.ninebox

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.littlestep.R
import com.example.littlestep.common.NineBoxConstants
import com.example.littlestep.dao.entity.nineBox.NineBoxDetailEntity
import es.dmoral.toasty.Toasty

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class CardView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {


    //    private var title: String;
    private var nineBoxDetailEntity: NineBoxDetailEntity? = null

    private var onClickListener: OnCardViewFunctionClickListener? = null;

    private var tv_title_in_card: TextView;
    private var tv_content_in_card: TextView;
    private var ibtn_funtion: ImageButton;
    private var iv_status: ImageView;

    init {
        LayoutInflater.from(context).inflate(R.layout.card_view, this)
        tv_title_in_card = findViewById(R.id.tv_title_in_card)
        tv_content_in_card = findViewById(R.id.tv_content_in_card)
        iv_status = findViewById(R.id.iv_status)
        ibtn_funtion = findViewById(R.id.ibtn_funtion)
        val typedArray: TypedArray = context!!.obtainStyledAttributes(
            attrs, R.styleable.CardView
        )

        ibtn_funtion.setOnClickListener {
            if (TextUtils.isEmpty(tv_content_in_card.text)) {
                Toasty.info(context, "请先输入计划！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            onClickListener?.onCardViewFunctionClick(this, ibtn_funtion)
        }
        setTitle(typedArray.getString(R.styleable.CardView_cardTitle).toString())
        setContent(typedArray.getString(R.styleable.CardView_cardContent)?.toString() ?: "")
        typedArray.recycle()
    }

    open fun setOnCardViewFunctionClickListener(listener: OnCardViewFunctionClickListener) {
        this.onClickListener = listener
    }

    /**
     * 设置卡片内容状态
     */
    private fun setStatus(status: Int) {

        when (status) {
            NineBoxConstants.DetailStatus.FINISH -> {
                iv_status.visibility = VISIBLE
                iv_status.setBackgroundResource(R.drawable.ic_finish)
            }

            NineBoxConstants.DetailStatus.DROP -> {
                iv_status.visibility = VISIBLE
                iv_status.setBackgroundResource(R.drawable.ic_drop)
            }

            else -> {
                iv_status.visibility = GONE
            }
        }
    }

    open fun setNineBoxDetailEntity(nineBoxDetailEntity: NineBoxDetailEntity) {
        this.nineBoxDetailEntity = nineBoxDetailEntity
        setContent(nineBoxDetailEntity.itemValue)
        setContent(nineBoxDetailEntity.itemValue)
        setStatus(nineBoxDetailEntity.status)
    }

    open fun getNineBoxDetailEntity(): NineBoxDetailEntity? {
        return this.nineBoxDetailEntity
    }

    /**
     * 设置标题
     */
    private fun setTitle(title: String) {
        tv_title_in_card.text = title
    }

    private fun setContent(content: String) {
        tv_content_in_card.text = content
    }

//    fun getContent(): String? {
//        return tv_content_in_card.text?.toString();
//    }

    interface OnCardViewFunctionClickListener {
        fun onCardViewFunctionClick(cardView: CardView, funView: View)
    }
}