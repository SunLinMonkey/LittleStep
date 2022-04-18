package com.example.littlestep.utils

/**
 * 通用工具类
 * create on 2022/4/18 15:04
 * Email:923998007@qq.com
 * @author lin
 */
class CommonUtils {
    companion object {


        var lastClickTime: Long = 0

        /**
         * 是否连点了。
         */
        open fun doubleClick(): Boolean {
            return doubleClick(500);
        }

        /**
         * 是否连点了。
         */
        open fun doubleClick(duringTime: Long): Boolean {
            val currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastClickTime < duringTime) {
                lastClickTime = currentTimeMillis;
                return true;
            }
            lastClickTime = currentTimeMillis;
            return false;
        }
    }
}