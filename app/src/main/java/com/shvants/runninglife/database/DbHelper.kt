package com.shvants.runninglife.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.shvants.runninglife.database.Contract.PRIMARY_KEY
import com.shvants.runninglife.database.Contract.SQL_TABLE_CREATE_TEMPLATE
import com.shvants.runninglife.database.fields.*
import com.shvants.runninglife.utils.Const.COMMA
import com.shvants.runninglife.utils.Const.EMPTY

class DbHelper(context: Context?) :
        SQLiteOpenHelper(context, Contract.DATABASE_NAME, null, Contract.DATABASE_VERSION, null),
        IDbOperation {

    override fun onCreate(db: SQLiteDatabase?) {
        for (table in Contract.getTables()) {
            val createTableString = createTableString(table)
            db?.execSQL(createTableString)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        throw UnsupportedOperationException(Contract.UPGRADE_NOT_SUPPORTED)
    }

    override fun query(sql: String, vararg params: String): Cursor {
        val readableDatabase = readableDatabase
        val cursor: Cursor

        readableDatabase.beginTransaction()
        try {
            cursor = readableDatabase.rawQuery(sql, params)
            readableDatabase.setTransactionSuccessful()
        } finally {
            readableDatabase.endTransaction()
        }

        return cursor
    }

    override fun insert(tableName: String, contentValues: ContentValues): Long {
        val writableDatabase = writableDatabase
        val insert: Long

        writableDatabase.beginTransaction()
        try {
            insert = writableDatabase.insertWithOnConflict(tableName, null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE)
            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }

        return insert
    }

    override fun delete(tableName: String, sql: String, vararg params: String): Int {
        val writableDatabase = writableDatabase
        val delete: Int

        writableDatabase.beginTransaction()
        try {
            delete = writableDatabase.delete(tableName, sql, params)
            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }

        return delete
    }

    private fun createTableString(jClass: Class<*>): String {
        val tableName = getTableName(jClass)

        return if (tableName != EMPTY) {
            val fields = jClass.declaredFields
            val builder = StringBuilder()

            for (i in fields.indices) {
                val field = fields[i]

                if (!field.isSynthetic) {
                    var type = EMPTY
                    val primaryKeyAnnotation = field.getAnnotation(DbPrimaryKey::class.java)
                    val annotations = field.annotations

                    for (annotationType in annotations) {
                        when (annotationType) {
                            is DbString -> type = annotationType.name
                            is DbLong -> type = annotationType.name
                            is DbInt -> type = annotationType.name
                            is DbDouble -> type = annotationType.name
                            is DbBoolean -> type = annotationType.name
                            is DbPrimaryKey -> { }
                            else -> throw IllegalStateException(Contract.ANNOTATION_NO_TYPE)
                        }
                    }

                    val fieldName = field.name
                    val primaryKey = if (primaryKeyAnnotation != null) PRIMARY_KEY else EMPTY
                    val template = "$fieldName $type$primaryKey"

                    builder.append(template).append(COMMA)
                }
            }

            builder.deleteCharAt(builder.lastIndex)

            String.format(SQL_TABLE_CREATE_TEMPLATE, tableName, builder.toString())
        } else {
            EMPTY
        }
    }

    private fun getTableName(jClass: Class<*>): String {
        return jClass.getAnnotation(Table::class.java)?.name ?: EMPTY
    }
}