package com.shvants.runninglife.repository

import android.content.ContentValues
import android.content.Context
import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.DbHelper
import com.shvants.runninglife.model.database.SummaryActivityDb
import com.shvants.runninglife.model.database.SummaryAthleteModel


class DbRepository(context: Context?) {

    private val dbHelper = DbHelper(context)

    fun getLoggedInAthlete(id: Long): SummaryAthleteModel? {
        dbHelper.query("SELECT * FROM ${Contract.AthleteEntry.TABLE_NAME} WHERE _ID = $id")
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

    fun getAthleteActivities(id: Long): List<SummaryActivityDb>? {
        val list = ArrayList<SummaryActivityDb>()
        list.add(SummaryActivityDb("1", "1", "Morning Run", "3663",
                "Run", "2019-05-07T12:12:12Z", "12.32", "3.36",
                "google_disk"))

        return list
    }

    fun setLoggedInAthlete(contentValues: ContentValues): Long {
        return dbHelper.insert(Contract.AthleteEntry.TABLE_NAME, contentValues)
    }

}