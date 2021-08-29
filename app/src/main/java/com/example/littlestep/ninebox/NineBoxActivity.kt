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
import com.example.littlestep.common.Business
import com.example.littlestep.common.NineBoxConstants
import com.example.littlestep.common.RecordKeyMaker
import com.example.littlestep.dao.DatabaseManager
import com.example.littlestep.dao.entity.nineBox.NineBoxHolderEntity
import com.example.littlestep.dao.entity.nineBox.NineBoxDetailEntity
import com.example.littlestep.dao.entity.nineBox.NineBoxDetailEntityDao
import com.example.littlestep.dao.entity.nineBox.NineBoxHolderEntityDao
import java.util.*

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class NineBoxActivity : BaseActivity(), View.OnClickListener {


    private val TAG = this.javaClass.simpleName;

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

    private lateinit var recordKey: String
    private lateinit var recordEntity: NineBoxHolderEntity

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
        displayBackData(currentTag, content)

        saveDetailRecord(currentTag, content)
    }

    /**
     * 输入返回，结果分发到界面显示
     */
    private fun displayBackData(currentTag: String?, content: String?) {
        when (currentTag) {
            NineBoxConstants.NineBoxItemKey.CAUSE -> {
                content?.toString()?.let { view_cause.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.HEALTHY -> {
                content?.toString()?.let { view_healthy.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.MONEY -> {
                content?.toString()?.let { view_money.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.CONTACTS -> {
                content?.toString()?.let { view_contacts.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.FAMILY -> {
                content?.toString()?.let { view_family.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.STUDY -> {
                content?.toString()?.let { view_study.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.LEISURE -> {
                content?.toString()?.let { view_leisure.setContent(it) }
            }
            NineBoxConstants.NineBoxItemKey.HEART -> {
                content?.toString()?.let { view_heart.setContent(it) }
            }
        }
    }

    override fun initView() {
    }

    override fun initData() {
        Log.e(TAG, "initData: ")
        recordKey = RecordKeyMaker.createKey(Business.TYPE_NINE_BOX);
        readRecord()
    }

    /**
     * 读取记录
     */
    private fun readRecord() {
        val list =
            DatabaseManager.instance.getDaoSession(this).nineBoxHolderEntityDao.queryBuilder()
                .where(NineBoxHolderEntityDao.Properties.RecordKey.eq(recordKey)).list()

        if (list?.size!! > 0) {
            recordEntity = list[0]
            readDetailRecord()
        } else {
            createMainRecord()
        }

    }

    private fun readDetailRecord() {
        val list =
            DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.queryBuilder()
                .where(
                    NineBoxDetailEntityDao.Properties.RecordKey.eq(recordEntity.recordKey)
                ).list()

        list.forEach { displayBackData(it.itemKey, it.itemValue) }

    }

    /**
     * 创建记录的主
     */
    private fun createMainRecord() {
        recordEntity =
            NineBoxHolderEntity()
        recordEntity.recordKey = recordKey
        recordEntity.createTime = Date()
        DatabaseManager.instance.getDaoSession(this).nineBoxHolderEntityDao.insert(recordEntity)
    }

    /**
     * 输入界面返回，触发保存，只修改明细条目
     */
    private fun saveDetailRecord(currentTag: String?, content: String?) {
        val recordDetail: NineBoxDetailEntity;
        val list =
            DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.queryBuilder()
                .where(
                    NineBoxDetailEntityDao.Properties.RecordKey.eq(recordEntity.recordKey),
                    NineBoxDetailEntityDao.Properties.ItemKey.eq(currentTag)
                ).list()

        if (list == null || list.isEmpty()) {
            recordDetail = NineBoxDetailEntity()
            recordDetail.itemKey = currentTag
            recordDetail.itemValue = content
            recordDetail.updateValueTime = Date()
            recordDetail.crateTime = Date()
            recordDetail.status = NineBoxConstants.DetailStatus.BLANK
            recordDetail.recordKey = recordEntity.recordKey
        } else {
            recordDetail = list[0];
            recordDetail.itemKey = currentTag
            recordDetail.itemValue = content
            recordDetail.updateValueTime = Date()
        }
        DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.insertOrReplaceInTx(
            recordDetail
        )

    }

    @OnClick(
        R.id.view_cause,
        R.id.view_healthy,
        R.id.view_money,
        R.id.view_contacts,
        R.id.view_family,
        R.id.view_study,
        R.id.view_leisure,
        R.id.view_heart,
        R.id.view_today
    )
    override fun onClick(view: View) {
        Log.i("TAG", "点击事件")
        when (view.id) {
            R.id.view_today -> {
                showRecordToday()
            }

            R.id.view_cause -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.CAUSE,
                    view_cause.getContent().toString()
                );
            }

            R.id.view_healthy -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.HEALTHY,
                    view_healthy.getContent().toString()
                );
            }

            R.id.view_money -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.MONEY,
                    view_money.getContent().toString()
                );
            }

            R.id.view_contacts -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.CONTACTS,
                    view_contacts.getContent().toString()
                );
            }

            R.id.view_family -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.FAMILY,
                    view_family.getContent().toString()
                );
            }

            R.id.view_study -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.STUDY,
                    view_study.getContent().toString()
                );
            }

            R.id.view_leisure -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.LEISURE,
                    view_leisure.getContent().toString()
                );
            }

            R.id.view_heart -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.HEART,
                    view_heart.getContent().toString()
                );
            }
        }
    }

    private fun showRecordToday() {
        val list =
            DatabaseManager.instance.getDaoSession(this).nineBoxHolderEntityDao.queryBuilder()
                .where(NineBoxHolderEntityDao.Properties.RecordKey.eq(recordKey)).list()
        for (nineBoxHolderEntity in list) {
            Log.e(TAG, "showRecordToday: $nineBoxHolderEntity")
        }
    }
}