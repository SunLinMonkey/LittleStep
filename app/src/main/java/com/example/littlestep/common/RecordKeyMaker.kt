package com.example.littlestep.common

import com.example.littlestep.utils.DateUtils

/**
 * create on 2021/7/26 21:28
 * Email:923998007@qq.com
 * @author lin
 */
class RecordKeyMaker {

    companion object {

        /**
         *
         */
        @JvmStatic
        fun createKey(functionType: String): String {
            val dateNow = DateUtils.getDateNow(DateUtils.dateFormatFour)
            return functionType + dateNow;
        }
    }

}