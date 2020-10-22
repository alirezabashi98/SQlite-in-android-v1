package com.arb.database.sqlite.data.local.db.dao

import android.content.ContentValues
import com.arb.database.sqlite.data.local.db.AppDatabase

abstract class BaseDao<T>(val appDatabase: AppDatabase) {

    val contentValues = ContentValues()

    var query = ""

    var data:MutableList<T> = ArrayList()

    abstract fun save(entity: T): Boolean

    abstract fun save(id: String, entity: T): Boolean

    abstract fun findAll(): List<T>

    abstract fun find(columnName: String, columnValue: String): List<T>

    abstract fun delete(id: String): Boolean

}