package com.example.littlestep.ninebox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.example.littlestep.R
import com.example.littlestep.base.BaseActivity
import com.example.littlestep.common.NineBoxConstants

/**
 * 内容输入界面
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class InputActivity : BaseActivity() {


    val TYPE_MAP = mapOf(
        NineBoxConstants.NineBoxItemKey.CAUSE to "事业",
        NineBoxConstants.NineBoxItemKey.HEALTHY to "健康",
        NineBoxConstants.NineBoxItemKey.MONEY to "理财",
        NineBoxConstants.NineBoxItemKey.CONTACTS to "人脉",
        NineBoxConstants.NineBoxItemKey.FAMILY to "家庭",
        NineBoxConstants.NineBoxItemKey.STUDY to "学习",
        NineBoxConstants.NineBoxItemKey.LEISURE to "休闲",
        NineBoxConstants.NineBoxItemKey.HEART to "心灵"
    )

    lateinit var currentTag: String;

    @BindView(R.id.tv_tag_tilte)
    lateinit var tagTitle: TextView

    @BindView(R.id.edt_input)
    lateinit var edtInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_input;
    }

    override fun initView() {
    }

    override fun initData() {

        val content =
            intent.getSerializableExtra(ResultContract.KEY_NINE_BOX_TO_MISSION_INPUT) as ResultContract.PushData
        currentTag = content.missionTag;
        tagTitle.text = TYPE_MAP[currentTag];
        edtInput.setText(content.missionText)

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
        intent.putExtra(
            ResultContract.KEY_MISSION_INPUT_BACK_NINE_BOX,
            ResultContract.BackData(currentTag, edtInput.text?.toString() ?: "")
        )
        //setResult接受两个参数：第一个是用于向上一个Activity返回处理结果，一般是RESULT_OK或者RESULT_CANCELED
        //第二个是带有数据的intent传递过去。最后调用finish销毁。
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}