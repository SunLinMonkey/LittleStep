package com.example.littlestep.dao

import android.content.Context
import com.example.littlestep.dao.entity.DaoMaster
import com.example.littlestep.dao.entity.DaoSession

/**
 * create on 2021/7/25 20:58
 * Email:923998007@qq.com
 * @author lin
 */
class DatabaseManager {
    private val DB_NAME = "test.db"
    private var mDevOpenHelper: DaoMaster.DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = DatabaseManager()
    }

    open fun initDao(context: Context) {
        val helper = DaoMaster.DevOpenHelper(context, "User")//创建的数据库名。
        val db = helper.writableDb

        mDaoSession = DaoMaster(db).newSession();
    }

    //
    init {
    }
}