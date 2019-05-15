package com.shvants.runninglife.strava

import android.os.Handler
import com.shvants.runninglife.utils.Const
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class StravaRequest(private val url: String) {

    fun get(): String {

        var result = Const.EMPTY
        val httpClient = OkHttpClient()

//        val handler = Handler()

        Thread(Runnable {
            val request = Request.Builder()
                    .url(url)
                    .build()

            val response = httpClient
                    .newCall(request)
                    .execute()

//            handler.post {  }
            result = response.body()?.string() ?: Const.EMPTY
        }).start()

        return result
    }

    fun post(body: FormBody): String {

        var result = Const.EMPTY
        val handler = Handler()

        Thread(Runnable {
            val httpClient = OkHttpClient()
            val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

            val response = httpClient
                    .newCall(request)
                    .execute()

//            handler.post { result = response.body()?.string() ?: Const.EMPTY }
            result = response.body()?.string() ?: Const.EMPTY
        }).start()

        return result
    }
}