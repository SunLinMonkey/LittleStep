package com.example.littlestep.ninebox

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
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
import com.example.littlestep.mvpBase.MvpActivity
import com.example.littlestep.popwindow.NineBoxFuncAttachPopup
import com.example.littlestep.popwindow.NineBoxFuncAttachPopup.OnClickTabListener
import com.example.littlestep.utils.CommonUtils
import com.lxj.xpopup.XPopup
import es.dmoral.toasty.Toasty
import java.util.*

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class NineBoxActivity : MvpActivity<NineBoxContract.IPresenter>(), NineBoxContract.IView,
    View.OnClickListener,
    CardView.OnCardViewFunctionClickListener {


    private val TAG = this.javaClass.simpleName

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

    /**
     * 输入界面的onActivityResult回调
     */
    private lateinit var registerForActivityResult: ActivityResultLauncher<ResultContract.PushData>

    override fun getLayoutRes(): Int {
        return R.layout.activity_nine_box;
    }


    override fun initView() {
        view_cause.setOnCardViewFunctionClickListener(this)
        view_healthy.setOnCardViewFunctionClickListener(this)
        view_money.setOnCardViewFunctionClickListener(this)
        view_contacts.setOnCardViewFunctionClickListener(this)
        view_family.setOnCardViewFunctionClickListener(this)
        view_study.setOnCardViewFunctionClickListener(this)
        view_leisure.setOnCardViewFunctionClickListener(this)
        view_heart.setOnCardViewFunctionClickListener(this)
    }

    override fun initData() {
        Log.e(TAG, "initData: ")
        recordKey = RecordKeyMaker.createKey(Business.TYPE_NINE_BOX)
        readRecord()

        registerForActivityResult =
            registerForActivityResult(ResultContract(), ActivityResultCallback {
                if (it != null) {
                    Log.e(TAG, "onActivityResult: ${it.missionTag}    ${it.missionText}")
                    val saveDetailRecord = saveDetailRecord(it.missionTag, it.missionText)
                    displayBackData(saveDetailRecord)
                }
            })
    }

    /**
     * 去输入界面
     */
    private fun openInputView(tag: String, nineBoxDetailEntity: NineBoxDetailEntity?) {
        when (nineBoxDetailEntity?.status) {

            NineBoxConstants.DetailStatus.FINISH -> {
                Toasty.info(this, "任务已完成，不能重新编辑", Toast.LENGTH_LONG).show()
            }

            NineBoxConstants.DetailStatus.DROP -> {
                Toasty.info(this, "任务已放弃，请重新打开后编辑", Toast.LENGTH_LONG).show()
            }

            else -> {
                registerForActivityResult.launch(
                    ResultContract.PushData(
                        tag,
                        nineBoxDetailEntity?.itemValue ?: ""
                    )
                )
            }
        }
    }

    /**
     * 输入返回，结果分发到界面显示
     */
    private fun displayBackData(nineBoxDetailEntity: NineBoxDetailEntity) {
        when (nineBoxDetailEntity.itemKey) {
            NineBoxConstants.NineBoxItemKey.CAUSE -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_cause.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.HEALTHY -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_healthy.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.MONEY -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_money.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.CONTACTS -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_contacts.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.FAMILY -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_family.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.STUDY -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_study.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.LEISURE -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_leisure.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
            NineBoxConstants.NineBoxItemKey.HEART -> {
                nineBoxDetailEntity.itemValue?.toString()
                    ?.let { view_heart.setNineBoxDetailEntity(nineBoxDetailEntity) }
            }
        }
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

        list.forEach { displayBackData(it) }

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
    private fun saveDetailRecord(currentTag: String?, content: String?): NineBoxDetailEntity {
        val recordDetail: NineBoxDetailEntity
        val list = getDetailInDatabase(currentTag)

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
        return recordDetail;
    }

    /**
     * 获取当前卡页的数据
     */
    private fun getDetailInDatabase(currentTag: String?): List<NineBoxDetailEntity>? {
        return DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.queryBuilder()
            .where(
                NineBoxDetailEntityDao.Properties.RecordKey.eq(recordEntity.recordKey),
                NineBoxDetailEntityDao.Properties.ItemKey.eq(currentTag)
            ).list()

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

        if (CommonUtils.doubleClick()) {
            //连点控制
            return
        }
        when (view.id) {
            R.id.view_today -> {
                showRecordToday()
            }

            R.id.view_cause -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.CAUSE,
                    view_cause.getNineBoxDetailEntity()
                );
            }

            R.id.view_healthy -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.HEALTHY,
                    view_healthy.getNineBoxDetailEntity()
                );
            }

            R.id.view_money -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.MONEY,
                    view_money.getNineBoxDetailEntity()
                );
            }

            R.id.view_contacts -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.CONTACTS,
                    view_contacts.getNineBoxDetailEntity()
                );
            }

            R.id.view_family -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.FAMILY,
                    view_family.getNineBoxDetailEntity()
                );
            }

            R.id.view_study -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.STUDY,
                    view_study.getNineBoxDetailEntity()
                );
            }

            R.id.view_leisure -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.LEISURE,
                    view_leisure.getNineBoxDetailEntity()
                );
            }

            R.id.view_heart -> {
                openInputView(
                    NineBoxConstants.NineBoxItemKey.HEART,
                    view_heart.getNineBoxDetailEntity()
                );
            }
        }
    }

    private fun showRecordToday() {
        val list =
            DatabaseManager.instance.getDaoSession(this).nineBoxHolderEntityDao.queryBuilder()
                .where(NineBoxHolderEntityDao.Properties.RecordKey.eq(recordKey)).list()
        for (nineBoxHolderEntity in list) {
            Log.e(TAG, "showRecordToday: ${nineBoxHolderEntity.toString()}")
        }


        val list1 =
            DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.queryBuilder()
                .where(
                    NineBoxDetailEntityDao.Properties.RecordKey.eq(recordEntity.recordKey)
                ).list()

        for (nineBoxHolderEntity in list1) {
            Log.e(
                TAG,
                "showRecordDetail: ${nineBoxHolderEntity.status}   ${nineBoxHolderEntity.itemKey}"
            )
        }
    }

    /**
     * 点击卡片下方的功能按钮
     */
    override fun onCardViewFunctionClick(cardView: CardView, funcView: View) {
        Log.e(TAG, "onCardViewFunctionClick: ${cardView.id}")
        when (cardView.id) {
            R.id.view_cause -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.CAUSE)
            }

            R.id.view_healthy -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.HEALTHY)
            }

            R.id.view_money -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.MONEY)
            }

            R.id.view_contacts -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.CONTACTS)
            }

            R.id.view_family -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.FAMILY)
            }

            R.id.view_study -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.STUDY)
            }

            R.id.view_leisure -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.LEISURE)
            }

            R.id.view_heart -> {
                showFunctionPopWindow(cardView, funcView, NineBoxConstants.NineBoxItemKey.HEART)
            }
        }
    }

    /**
     * 显示卡片功能按钮
     */
    private fun showFunctionPopWindow(cardView: CardView, funcView: View, cause: String) {
        XPopup.Builder(this).isDestroyOnDismiss(true)
            .hasShadowBg(false).isViewMode(true).atView(funcView)
            .asCustom(NineBoxFuncAttachPopup(this, object : OnClickTabListener {
                override fun clickTab(id: Int) {
                    dealCardFunction(id, cause, cardView)
                }
            })).show()
    }

    /**
     * 处理卡片功能
     */
    private fun dealCardFunction(functionId: Int, cause: String, cardView: CardView) {
        val detailInDatabase = getDetailInDatabase(cause)
        val recordDetail = detailInDatabase?.get(0) as NineBoxDetailEntity

        recordDetail.updateStatusTime = Date()
        recordDetail.status = getCardMissionStatus(functionId)

        DatabaseManager.instance.getDaoSession(this).nineBoxDetailEntityDao.insertOrReplaceInTx(
            recordDetail
        )
        cardView.setNineBoxDetailEntity(recordDetail)
    }

    /**
     * 返回点击页卡功能按钮后，应该赋予的页卡状态
     */
    private fun getCardMissionStatus(functionId: Int): Int {
        when (functionId) {
            NineBoxFuncAttachPopup.FINISH -> {
                return NineBoxConstants.DetailStatus.FINISH
            }

            NineBoxFuncAttachPopup.DROP -> {
                return NineBoxConstants.DetailStatus.DROP
            }

            NineBoxFuncAttachPopup.REOPEN -> {
                return NineBoxConstants.DetailStatus.DOING
            }
        }
        return NineBoxConstants.DetailStatus.DOING
    }

    override fun registerPresenter(): Class<out NineBoxContract.IPresenter> {
        return NineBoxPresenter::class.java
    }
}