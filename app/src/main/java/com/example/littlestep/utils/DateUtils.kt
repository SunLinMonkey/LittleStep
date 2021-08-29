package com.example.littlestep.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * create on 2021/7/26 21:34
 * Email:923998007@qq.com
 * @author lin
 */
class DateUtils {
    companion object {

        val dateFormatOne = "YYYYMMDDHHmmss";
        val dateFormatTwo = "YYYY-MM-DD HH:mm:ss";
        val dateFormatThree = "YYYY-MM-DD";
        val dateFormatFour = "YYYYMMDD";

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