package com.example.littlestep.ninebox

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.littlestep.R

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class CardView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {


    private var title: String;
    private var content: String;

    private var tv_title_in_card: TextView;
    private var tv_content_in_card: TextView;

    init {
        LayoutInflater.from(context).inflate(R.layout.card_view, this)
        tv_title_in_card = findViewById(R.id.tv_title_in_card)
        tv_content_in_card = findViewById(R.id.tv_content_in_card)
        val typedArray: TypedArray = context!!.obtainStyledAttributes(
            attrs, R.styleable.CardView
        )
        title = typedArray.getString(R.styleable.CardView_cardTitle).toString()
        content = typedArray.getString(R.styleable.CardView_cardContent)?.toString() ?: ""

        setTitle(this.title)
        setContent(this.content)
        typedArray.recycle()
    }


    open fun setTitle(title: String) {
        this.title = title;
        tv_title_in_card.text = this.title
    }

    open fun getTitle(): String? {
        return tv_title_in_card.text?.toString();
    }

    open fun setContent(content: String) {
        this.content = content
        tv_content_in_card.text = this.content
    }

    open fun getContent(): String? {
        return tv_content_in_card.text?.toString();
    }
}