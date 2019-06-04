package com.shvants.runninglife.strava

import com.shvants.runninglife.utils.Const
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request


class StravaRequest {

    fun makeAthleteActivitiesRequest(token: String, page: Int): String {
        val url = StravaHelper.getAthleteActivitiesUrl(token, page)

        return get(url)
    }

    fun get(url: String): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .url(url)
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }

    fun get(url: String, token: String): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .url(url)
                .header(StravaHelper.AUTHORIZATION, "${StravaHelper.BEARER} $token")
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }

    fun post(url: String, body: FormBody): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .url(url)
                .post(body)
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }

    fun post(url: String, token: String, body: FormBody): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .header(StravaHelper.AUTHORIZATION, "${StravaHelper.BEARER} $token")
                .url(url)
                .post(body)
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }
}
