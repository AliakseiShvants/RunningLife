package com.shvants.runninglife.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log.d
import com.shvants.runninglife.database.fields.*
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.COMMA
import com.shvants.runninglife.utils.Const.Database.PRIMARY_KEY
import com.shvants.runninglife.utils.Const.Database.SQL_TABLE_CREATE_TEMPLATE
import com.shvants.runninglife.utils.Const.EMPTY

class DbHelper : SQLiteOpenHelper, IDbOperation {

    private val TAG = DbHelper::class.simpleName

    constructor(context: Context?,
                factory: SQLiteDatabase.CursorFactory?,
                version: Int) : super(context, Const.Database.DATABASE_NAME, factory, version) {
        d(TAG, "inside constructor")
    }

    constructor(context: Context?,
                factory: SQLiteDatabase.CursorFactory?,
                version: Int,
                errorHandler: DatabaseErrorHandler?) :
            super(context, Const.Database.DATABASE_NAME, factory, version, errorHandler)

    override fun onCreate(db: SQLiteDatabase?) {
        d(TAG, "inside onCreate")

        for (table in Contract.getTables()) {
            val createTableString = createTableString(table)

            d(TAG, createTableString)

            db?.execSQL(createTableString)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        throw UnsupportedOperationException("Upgrade not supported")
    }

    override fun query(sql: String, params: String): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(tableName: String, contentValues: ContentValues): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createTableString(jClass: Class<*>): String? {
        val tableName = getTableName(jClass)

        if (tableName != null) {
            val fields = jClass.fields
            val builder = StringBuilder()

            for (i in fields.indices) {
                val field = fields[i]
                var type = EMPTY
                val primaryKeyAnnotation = field.getAnnotation(dbPrimaryKey::class.java)
                val annotations = field.annotations

                for (annotationType in annotations) {
                    when (annotationType) {
                        is dbString -> type = annotationType.name
                        is dbLong -> type = annotationType.name
                        is dbInt -> type = annotationType.name
                        is dbDouble -> type = annotationType.name
                        is dbBoolean -> type = annotationType.name
                        !is dbPrimaryKey ->
                            throw IllegalStateException("Field don't have type annotation")
                    }

                    val fieldName = field.name
                    var primaryKey = EMPTY

                    if (primaryKeyAnnotation != null) {
                        primaryKey = PRIMARY_KEY
                    }

                    val template = "$fieldName $type $primaryKey"

                    builder.append(template)

                    if (i != fields.size - 1) {
                        builder.append(COMMA)
                    }
                }
            }

            return String.format(SQL_TABLE_CREATE_TEMPLATE, tableName, builder.toString())
        } else {
            return EMPTY
        }
    }

//    private fun getTableName(jClass: Class<*>): String? {
//        val annotation = kClass.annotations.first { it.annotationClass == Table::class }
//
//        Log.d(TAG, " $annotation")
//        return null
//    }

    private fun getTableName(jClass: Class<*>): String? {
        val annotation = jClass.getAnnotation(Table::class.java)

        return annotation?.name
    }
}
//class DatabaseHelper : SQLiteOpenHelper, IDatabaseOperation {

//    override fun onCreate(db: SQLiteDatabase) {
//        for (table in Contract.getTables()) {
//
//            val createTableString = getCreateTableString(table)
//
//            Log.d("Magic", createTableString)
//
//            if (createTableString != "") {
//                db.execSQL(createTableString)
//            }
//        }
//    }

//
//    fun query(pSql: String, vararg pParams: String): Cursor {
//        val readableDatabase = readableDatabase
//
//        readableDatabase.beginTransaction()
//
//        val cursor: Cursor
//
//        try {
//            cursor = readableDatabase.rawQuery(pSql, pParams)
//            readableDatabase.setTransactionSuccessful()
//        } finally {
//            readableDatabase.endTransaction()
//        }
//
//        return cursor
//    }
//
//    fun insert(pTableName: String, pContentValues: ContentValues): Long {
//        val writableDatabase = writableDatabase
//
//        writableDatabase.beginTransaction()
//
//        val insert: Long
//
//        try {
//            insert = writableDatabase.insertWithOnConflict(pTableName, null, pContentValues, SQLiteDatabase.CONFLICT_REPLACE)
//            writableDatabase.setTransactionSuccessful()
//        } finally {
//            writableDatabase.endTransaction()
//        }
//
//        return insert
//    }
//
//    fun delete(pTableName: String, pSql: String, vararg pParams: String): Long {
//        val writableDatabase = writableDatabase
//        writableDatabase.beginTransaction()
//
//        val delete: Int
//
//        try {
//            delete = writableDatabase.delete(pTableName, pSql, pParams)
//            writableDatabase.setTransactionSuccessful()
//        } finally {
//            writableDatabase.endTransaction()
//        }
//
//        return delete.toLong()
//    }
//}