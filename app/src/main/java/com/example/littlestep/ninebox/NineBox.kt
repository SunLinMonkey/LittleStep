package com.example.littlestep.ninebox

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.example.littlestep.base.BaseActivity
import com.example.littlestep.R

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class NineBox : BaseActivity(), View.OnClickListener {


    private val TAG = this.javaClass.simpleName;

    companion object TYPE_TAG {
        val CAUSE = "cause"
        val HEALTHY = "healthy"
        val MONEY = "money"
        val CONTACTS = "contacts"
        val FAMILY = "family"
        val STUDY = "study"
        val LEISURE = "leisure"
        val HEART = "heart"
    }


    @BindView(R.id.view_cause)
    lateinit var view_cause: CardView

    @BindView(R.id.view_healthy)
    lateinit var view_healthy: CardView

    @BindView(R.id.view_money)
    lateinit var view_money: CardView

    @BindView(R.id.view_contacts)
    lateinit var view_contacts: CardView

    @BindView(R.id.view_family)
    lateinit var view_family: CardView

    @BindView(R.id.view_study)
    lateinit var view_study: CardView

    @BindView(R.id.view_today)
    lateinit var view_today: TextView

    @BindView(R.id.view_leisure)
    lateinit var view_leisure: CardView

    @BindView(R.id.view_heart)
    lateinit var view_heart: CardView

    override fun getLayoutRes(): Int {
        return R.layout.activity_nine_box;
    }

    private fun openInputView(tag: String, text: String) {

        val intent = Intent(this, InputActivity::class.java)
        //putExtra接受的是键值对，第一个参数是键，用于后面取值；第二个是真正要传递的数据
        intent.putExtra(InputActivity.KEY_CONTENT, text)
        intent.putExtra(InputActivity.KEY_TAG, tag)
        startActivityForResult(intent, InputActivity.REQUEST_CODE_CARD_VIEW)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        val content = data?.getStringExtra(InputActivity.KEY_CONTENT)
        val currentTag = data?.getStringExtra(InputActivity.KEY_TAG)
        Log.e(TAG, "onActivityResult: $content    $currentTag")
        displayBackData(currentTag, content);

    }

    private fun displayBackData(currentTag: String?, content: String?) {

        when (currentTag) {
            CAUSE -> {
                content?.toString()?.let { view_cause.setContent(it) }
            }
            HEALTHY -> {
                content?.toString()?.let { view_healthy.setContent(it) }
            }
            MONEY -> {
                content?.toString()?.let { view_money.setContent(it) }
            }
            CONTACTS -> {
                content?.toString()?.let { view_contacts.setContent(it) }
            }
            FAMILY -> {
                content?.toString()?.let { view_family.setContent(it) }
            }
            STUDY -> {
                content?.toString()?.let { view_study.setContent(it) }
            }
            LEISURE -> {
                content?.toString()?.let { view_leisure.setContent(it) }
            }
            HEART -> {
                content?.toString()?.let { view_heart.setContent(it) }
            }
        }
    }

    override fun initView() {
    }

    override fun initData() {
    }

    @OnClick(
        R.id.view_cause,
        R.id.view_healthy,
        R.id.view_money,
        R.id.view_contacts,
        R.id.view_family,
        R.id.view_study,
        R.id.view_leisure,
        R.id.view_heart
    )
    override fun onClick(view: View) {
        Log.i("TAG", "点击事件")
        when (view.id) {
            R.id.view_cause -> {
                openInputView(CAUSE, view_cause.getContent().toString());
            }

            R.id.view_healthy -> {
                openInputView(HEALTHY, view_healthy.getContent().toString());
            }

            R.id.view_money -> {
                openInputView(MONEY, view_money.getContent().toString());
            }

            R.id.view_contacts -> {
                openInputView(CONTACTS, view_contacts.getContent().toString());
            }

            R.id.view_family -> {
                openInputView(FAMILY, view_family.getContent().toString());
            }

            R.id.view_study -> {
                openInputView(STUDY, view_study.getContent().toString());
            }

            R.id.view_leisure -> {
                openInputView(LEISURE, view_leisure.getContent().toString());
            }

            R.id.view_heart -> {
                openInputView(HEART, view_heart.getContent().toString());
            }
        }
    }
}