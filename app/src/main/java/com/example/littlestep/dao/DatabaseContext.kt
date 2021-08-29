package com.example.littlestep.dao

import android.content.Context
import android.content.ContextWrapper
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import java.io.File
import kotlin.math.log

/**
 * create on 2021/7/26 22:47
 * Email:923998007@qq.com
 * @author lin
 */
class DatabaseContext(base: Context?) : ContextWrapper(base) {

    var path = ""

    constructor(base: Context?, path: String) : this(base) {
        if (!TextUtils.isEmpty(path)) {
            this.path = path
        }
    }

    override fun openOrCreateDatabase(
        name: String?,
        mode: Int,
        factory: SQLiteDatabase.CursorFactory?
    ): SQLiteDatabase {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null)
    }

    override fun openOrCreateDatabase(
        name: String?,
        mode: Int,
        factory: SQLiteDatabase.CursorFactory?,
        errorHandler: DatabaseErrorHandler?
    ): SQLiteDatabase {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null)
    }

    override fun getDatabasePath(name: String?): File {

        Log.e("9527", "getDatabasePath: " + path)

        val externalFilesDir = getExternalFilesDir(path)

        val dbFile = File(externalFilesDir?.absoluteFile, name);
        if (!dbFile.exists()) {
            dbFile.createNewFile()
        }
        return dbFile
    }
}