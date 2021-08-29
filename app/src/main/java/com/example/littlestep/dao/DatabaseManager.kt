package com.example.littlestep.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.littlestep.dao.entity.DaoMaster
import com.example.littlestep.dao.entity.DaoSession

/**
 * create on 2021/7/25 20:58
 * Email:923998007@qq.com
 * @author lin
 */
open class DatabaseManager {
    private val DB_NAME = "myStep.db"
    private var mDevOpenlper: DaoMaster.DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = DatabaseManager()
    }

    open fun initDao(context: Context) {
        Log.e("9527", "initDao: " + this.javaClass.simpleName)

        mDevOpenlper = DaoMaster.DevOpenHelper(
            DatabaseContext(context, "/LinPro/Step"),
            DB_NAME
        )//创建的数据库名。
    }

    fun getReadableDataBase(context: Context): SQLiteDatabase? {
        return mDevOpenlper?.readableDatabase
    }

    fun getWriteableDataBase(context: Context): SQLiteDatabase? {
        return mDevOpenlper?.writableDatabase
    }

    fun getDaoMaster(context: Context): DaoMaster? {
        if (null == mDaoMaster) {
            synchronized(DatabaseManager::class) {
                if (null == mDaoMaster) {
                    mDaoMaster = DaoMaster(getWriteableDataBase(context))
                }
            }
        }
        return mDaoMaster
    }

    fun getDaoSession(context: Context): DaoSession {
        if (null == mDaoSession) {
            synchronized(DatabaseManager::class) {
                if (null == mDaoSession) {
                    mDaoSession = getDaoMaster(context)?.newSession()
                }
            }
        }
        return mDaoSession!!
    }
}