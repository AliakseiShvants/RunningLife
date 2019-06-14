package com.shvants.runninglife.repository

import android.content.ContentValues
import android.content.Context
import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.DbHelper
import com.shvants.runninglife.model.database.SummaryAthleteModel


class DbRepository(context: Context?) {

    private val dbHelper = DbHelper(context)

    fun getLoggedInAthlete(id: Long): SummaryAthleteModel? {
        dbHelper.query("$SELECT_FROM ${Contract.AthleteEntry.TABLE_NAME} $WHERE_ID $id")
                .use { cursor ->
                    if (cursor.moveToFirst()) {
                        return SummaryAthleteModel(
                                _id = cursor.getLong(cursor.getColumnIndex(Contract.ID)),
                                fullname = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.FULLNAME)),
                                location = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.LOCATION)),
                                profile = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.PROFILE)),
                                profile_medium = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.PROFILE_MEDIUM)),
                                sex = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.SEX)),
                                summit = cursor.getInt(cursor.getColumnIndex(Contract.AthleteEntry.SUMMIT)))
                    }
                }

        return null
    }

    fun setLoggedInAthlete(contentValues: ContentValues): Long {
        return dbHelper.insert(Contract.AthleteEntry.TABLE_NAME, contentValues)
    }

    companion object {
        const val SELECT_FROM = "SELECT * FROM"
        const val WHERE_ID = "WHERE _ID ="
    }
}