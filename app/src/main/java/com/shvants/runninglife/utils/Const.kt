package com.shvants.runninglife.utils

object Const {

    const val ONE = 1
    const val FI = 1.6
    const val ZERO = 0
    const val ZERO_LONG = 0L
    const val MINUS_LONG = -1L
    const val ZERO_DOUBLE = 0.0
    const val DOT = "."
    const val COLON = ":"
    const val EMPTY = ""
    const val COMMA = ","
    const val SLASH = "/"
    const val INTEGER = "INTEGER"
    const val REAL = "REAL"
    const val LONG = "LONG"
    const val STRING = "STRING"

    const val ACTIVITY_ID = "ACTIVITY_ID"
    const val ATHLETE_ID = "ATHLETE_ID"
    const val CLUB_ID = "CLUB_ID"
    const val FULLNAME = "FULLNAME"
    const val KUDOS = "KUDOS"
    const val LOCATION = "LOCATION"
    const val PROFILE_MEDIUM = "PROFILE_MEDIUM"
    const val PROFILE = "PROFILE"
    const val SUMMIT = "SUMMIT"

    const val ONE_COMMENT = "1 comment"
    const val COMMENTS = "comments"

    object FeedFragment {
        val TITLE = "Feed"
    }

    object ClubsFragment {
        val TITLE = "Clubs"
    }

    object MoveRecordFragment {
        val TITLE = "Move record"
    }

    object Database {
        val MOVING_TIME = "MOVING_TIME"
        val DISTANCE = "DISTANCE"
        val NAME = "NAME"
        val TYPE = "TYPE"
        val START_DATE = "START_DATE"
        val AVG_SPEED = "AVG_SPEED"
        val MAP = "MAP"
        val ELEVATION = "ELEVATION"
        val CALORIES = "CALORIES"
        val AVG_HR = "AVG_HR"
        val AVG_WATTS = "AVG_WATTS"
    }

    object ERR {
        const val INTERNET_CONNECTION = "Check Internet connection and try again"
        const val ACTIVITIES_LOAD_ERR = "Can't load your activities."
        const val ACTIVITY_LOAD_ERR = "Can't load your activity."
        const val CLUB_LOAD_ERR = "Can't load club info."
        const val KUDOS_LOAD_ERR = "Can't load your kudos."
    }
}
