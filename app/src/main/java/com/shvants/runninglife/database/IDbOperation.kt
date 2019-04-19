package com.shvants.runninglife.database

import android.content.ContentValues
import android.database.Cursor

interface IDbOperation {

    fun query(sql: String, params: String): Cursor

    fun insert(tableName: String, contentValues: ContentValues): Long
}