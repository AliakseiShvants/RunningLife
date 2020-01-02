package com.shvants.runninglife.strava

import com.shvants.runninglife.utils.Const
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request


class StravaRequest {

    private fun delete(token: String?, url: String): Boolean {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .url(url)
                .header(AUTHORIZATION, "$BEARER $token")
                .delete()
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.isSuccessful
    }

    private fun get(token: String?, url: String): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .header(AUTHORIZATION, "$BEARER $token")
                .url(url)
                .get()
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }

    fun post(url: String, token: String?, body: FormBody): String {
        val httpClient = OkHttpClient()
        val request = Request
                .Builder()
                .header(AUTHORIZATION, "$BEARER $token")
                .url(url)
                .post(body)
                .build()
        val response = httpClient
                .newCall(request)
                .execute()

        return response.body()?.string() ?: Const.EMPTY
    }

    fun getAthleteActivities(token: String?, page: Int): String {
        val url = StravaHelper.getAthleteActivitiesUrl(page)

        return get(token, url)
    }

    fun getAthleteKudoers(token: String?, id: Long): String {
        val url = StravaHelper.getKudoersUrl(id)

        return get(token, url)
    }

    fun getAthleteActivity(token: String?, id: Long): String {
        val url = StravaHelper.getActivityUrl(id)

        return get(token, url)
    }

    fun getAthleteClubs(token: String?): String {
        val url = StravaHelper.getClubsUrl()

        return get(token, url)
    }

    fun getClubRequest(token: String?, id: Int): String {
        val url = StravaHelper.getClubUrl(id)

        return get(token, url)
    }

    fun deleteActivityRequest(token: String?, id: Long): Boolean {
        val url = StravaHelper.deleteActivityUrl(id)

        return delete(token, url)
    }


    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
    }
}
