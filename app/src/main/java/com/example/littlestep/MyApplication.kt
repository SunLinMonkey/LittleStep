package com.example.littlestep

import android.app.Application
import com.example.littlestep.dao.DatabaseManager

/**
 * create on 2021/7/26 20:55
 * Email:923998007@qq.com
 * @author lin
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initDao()
    }

    private fun initDao() {
        DatabaseManager.instance.initDao(this);
    }
}