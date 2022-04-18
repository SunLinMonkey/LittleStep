package com.example.littlestep.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期工具类
 * create on 2021/7/26 21:34
 * Email:923998007@qq.com
 * @author lin
 */
class DateUtils {
    companion object {

        val dateFormatOne = "yyyyMMDDHHmmss";
        val dateFormatTwo = "yyyy-MM-DD HH:mm:ss";
        val dateFormatThree = "yyyy-MM-DD";
        val dateFormatFour = "yyyyMMDD";

        @JvmStatic
        open fun getDateNow(): String {
            return getDateNow(dateFormatTwo);
        }

        @JvmStatic
        open fun getDateNow(format: String): String {
            return SimpleDateFormat(format).format(Date())
        }
    }
}