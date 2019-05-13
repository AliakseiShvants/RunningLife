package com.shvants.runninglife.loaders

import android.os.AsyncTask
import com.shvants.runninglife.http.Url
import com.shvants.runninglife.json.ActivityListGson
import com.shvants.runninglife.json.ActivityListGsonParser
import com.squareup.okhttp.*
import java.io.IOException

class ActivityListTask : AsyncTask<Void, Void, ActivityListGson>() {

    override fun onPostExecute(result: ActivityListGson?) {
        super.onPostExecute(result)

        print(result.toString())
    }

    override fun doInBackground(vararg params: Void): ActivityListGson? {

        var activityList: ActivityListGson? = null

        val httpClient = OkHttpClient()

        val urlBuilder = HttpUrl.parse(Url.ACTIVITIES_BASE_URL).newBuilder()
        urlBuilder.addQueryParameter(Url.BEFORE, Url.BEFORE_VALUE)
        urlBuilder.addQueryParameter(Url.AFTER, Url.AFTER_VALUE)
        urlBuilder.addQueryParameter(Url.PAGE, Url.PAGE_VALUE)
        urlBuilder.addQueryParameter(Url.PER_PAGE, Url.PER_PAGE_VALUE)

        val url = urlBuilder.build().toString()

        val request = Request.Builder()
                .header(AUTHORIZATION, Url.ACCESS_TOKEN)
                .url(url)
                .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {

            }

            override fun onResponse(response: Response?) {
                val responseData = response?.body().toString()
                activityList = ActivityListGsonParser(responseData).parse() as ActivityListGson

            }

        })

        return activityList
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}