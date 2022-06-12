package com.example.littlestep.ninebox

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

import java.io.Serializable


/**
 * create on 2021/9/25 21:08
 * Email:923998007@qq.com
 * @author lin
 */
class ResultContract : ActivityResultContract<ResultContract.PushData, ResultContract.BackData>() {

    companion object INTENT_KEY {
        /**
         * 九宫格去输入页面
         */
        val KEY_NINE_BOX_TO_MISSION_INPUT = "NineBoxMissionInput"

        val KEY_MISSION_INPUT_BACK_NINE_BOX = "NineBoxMissionInputBack"
    }

    class PushData(tag: String, text: String) : Serializable {
        val missionText: String = text
        val missionTag: String = tag
    }

    class BackData(tag: String, text: String) : Serializable {
        val missionText: String = text
        val missionTag: String = tag
    }

    override fun createIntent(context: Context, input: PushData?): Intent {
        val intent = Intent(context, InputActivity::class.java)
        //putExtra接受的是键值对，第一个参数是键，用于后面取值；第二个是真正要传递的数据
        intent.putExtra(KEY_NINE_BOX_TO_MISSION_INPUT, input)
        return intent;
    }

    override fun parseResult(resultCode: Int, intent: Intent?): BackData? {
        if (resultCode != Activity.RESULT_OK) {
            return null;
        }
        return intent?.getSerializableExtra(KEY_MISSION_INPUT_BACK_NINE_BOX) as BackData
    }
}