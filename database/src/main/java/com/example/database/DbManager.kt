package com.example.database

import android.content.Context

/**
 * create on 2021/7/25 16:34
 * Email:923998007@qq.com
 * @author lin
 */
class DbManager private constructor() {

    private val DB_NAME = "test.db"
    private var mDevOpenHelper: DaoMaster.DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = DbManager()
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