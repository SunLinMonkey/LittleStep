package com.example.littlestep.ninebox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.littlestep.base.BaseActivity
import com.example.littlestep.R
import com.example.littlestep.ninebox.NineBox.TYPE_TAG

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class InputActivity : BaseActivity() {


    val TYPE_MAP = mapOf(
        TYPE_TAG.CAUSE to "事业", TYPE_TAG.HEALTHY to "健康", TYPE_TAG.MONEY to "理财",
        TYPE_TAG.CONTACTS to "人脉", TYPE_TAG.FAMILY to "家庭", TYPE_TAG.STUDY to "学习",
        TYPE_TAG.LEISURE to "休闲", TYPE_TAG.HEART to "心灵"
    )

    companion object INTENT_KEY {
        val KEY_CONTENT = "content"
        val KEY_TAG = "tag"

        val REQUEST_CODE_CARD_VIEW = 1
    }

    lateinit var currentTag: String;

    @BindView(R.id.tv_tag_tilte)
    lateinit var tagTitle: TextView

    @BindView(R.id.edt_input)
    lateinit var edtInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_input)
//        ButterKnife.bind(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_input;
    }

    override fun initView() {
    }

    override fun initData() {
        val content = intent.getStringExtra(KEY_CONTENT)
        currentTag = intent.getStringExtra(KEY_TAG)?.toString().toString()
        tagTitle.text = TYPE_MAP[currentTag];


    }

    init {

    }

    @OnClick(R.id.btn_cancel, R.id.btn_ensure)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_cancel -> {
                this.finish()
            }
            R.id.btn_ensure -> {
                back()
            }
        }
    }

    private fun back() {
        val intent = Intent()
        intent.putExtra(KEY_CONTENT, edtInput.text?.toString() ?: "")
        intent.putExtra(KEY_TAG, currentTag)
        //setResult接受两个参数：第一个是用于向上一个Activity返回处理结果，一般是RESULT_OK或者RESULT_CANCELED
        //第二个是带有数据的intent传递过去。最后调用finish销毁。
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}