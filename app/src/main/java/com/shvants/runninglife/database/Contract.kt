package com.shvants.runninglife.database

import com.shvants.runninglife.model.database.SummaryAthleteModel

object Contract {

    fun getTables(): List<Class<*>> {
        return listOf(SummaryAthleteModel::class.java)
    }

    object AthleteEntry {
        const val TABLE_NAME = "athlete"

        const val FULLNAME = "fullname"
        const val PROFILE_MEDIUM = "profile_medium"
        const val PROFILE = "profile"
        const val LOCATION = "location"
        const val SEX = "sex"
        const val SUMMIT = "summit"
    }

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "RunningLife.db"
    const val ID = "_id"
    const val PRIMARY_KEY = " PRIMARY KEY"
    const val SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s(%s);"
    const val SQL_DROP_DATABASE_TEMPLATE = "DROP DATABASE IF EXISTS %s;"
    const val SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s"

    const val TABLE_NAME = "athlete"

    const val ATHLETE_ID = "athlete_id"
    const val FULLNAME = "fullname"
    const val PROFILE_MEDIUM = "profile_medium"
    const val PROFILE = "profile"
    const val LOCATION = "location"
    const val SEX = "sex"
    const val SUMMIT = "summit"

    const val UPGRADE_NOT_SUPPORTED = "Upgrade not supported"
    const val ANNOTATION_NO_TYPE = "Field don't have type annotation"
}
