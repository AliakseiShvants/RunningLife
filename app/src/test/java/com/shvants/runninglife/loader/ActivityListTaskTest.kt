package com.shvants.runninglife.loader


import android.os.Handler
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaRequest
import com.shvants.runninglife.utils.ICallback
import io.mockk.impl.annotations.MockK
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class ActivityListTaskTest {

    @Test
    fun getAuthResponse() {
        val client = OkHttpClient()
        val code = "833dcb88c8257ad07333a6c7329209d55ff31bcb"

        val tokenUrl = StravaHelper.TOKEN_BASE_URL

        val requestBody = StravaHelper.getTokenBody(code)
        val request = Request.Builder().url(tokenUrl).post(requestBody).build()
        val response = client.newCall(request).execute()
        val jsonString = response.body()?.string() ?: ""

        Assert.assertNotNull(jsonString)
        Assert.assertTrue(jsonString.contains("access_token"))

    }

    @Test
    fun getAuthResponse2() {
        val TAG = "getAuthResponse2"
//        val executor = Executor { command -> command?.run() }
        val client = OkHttpClient()
        val code = "833dcb88c8257ad07333a6c7329209d55ff31bcb"

        val tokenUrl = StravaHelper.TOKEN_BASE_URL
        var jsonString = "empty"

        @MockK
        val handler = mock(Handler::class.java)

        val callback = object : ICallback<String> {
            override fun onResult(result: String) {
                println("callback onResult")
                jsonString = result
            }

            override fun onError(message: String) {
                println("callback onError")

            }
        }

        println("before")

        Thread(Runnable {
            println("Thread")

            val tokenBody = StravaHelper.getTokenBody(code)
            val result = StravaRequest().post(StravaHelper.TOKEN_BASE_URL, tokenBody)
            println("Thread=$result")

            handler.post {
                println("Thread handler.post")
                callback.onResult(result)
            }
        }).start()

        println("after")
    }
}