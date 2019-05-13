package com.shvants.runninglife.http

object Url {
    const val ACTIVITIES_BASE_URL = "https://www.strava.com/api/v3/athlete/activities"

    const val BEFORE = "before"
    const val AFTER = "after"
    const val PAGE = "page"
    const val PER_PAGE = "per_page"

    private const val ONE_WEEK_IN_SECONDS = 7 * 24 * 60 * 60
    val BEFORE_VALUE = (System.currentTimeMillis() / 1000).toInt().toString()
    val AFTER_VALUE = (System.currentTimeMillis() / 1000 - ONE_WEEK_IN_SECONDS).toInt().toString()
    const val PAGE_VALUE = "1"
    const val PER_PAGE_VALUE = "7"

    var ACCESS_TOKEN = "181988d5e26d222bbc3e7b5d78b91671718b0cb6"
}