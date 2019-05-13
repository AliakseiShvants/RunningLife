package com.shvants.runninglife.data.web.model

import android.os.AsyncTask
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.json.ActivityListGson
import com.shvants.runninglife.json.ActivityListGsonParser
import com.shvants.runninglife.loaders.ActivityListTask

class WebRepository private constructor() : Repository {

    override fun getLoggedAthlete(): SummaryAthleteWeb {
        return SummaryAthleteWeb.Builder()
                .id(1)
                .firstName("Aliaksei")
                .lastName("Shvants")
                .city("Grodno")
                .build()
    }

    override fun getAthleteSummaryActivities(id: Int?): List<SummaryActivityWeb> {
        val activitiesList = ArrayList<SummaryActivityWeb>()
        var json: String = ""

        val task = ActivityListTask(json)
//        val  task = JavaAsync()
//        task.execute()

        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
//        task.cancel(false)


//        var responseData: String? = ""
//        val httpClient = OkHttpClient()
//
//        val urlBuilder = HttpUrl.parse(Url.ACTIVITIES_BASE_URL).newBuilder()
//        urlBuilder.addQueryParameter(Url.BEFORE, Url.BEFORE_VALUE)
//        urlBuilder.addQueryParameter(Url.AFTER, Url.AFTER_VALUE)
//        urlBuilder.addQueryParameter(Url.PAGE, Url.PAGE_VALUE)
//        urlBuilder.addQueryParameter(Url.PER_PAGE, Url.PER_PAGE_VALUE)
//
//        val url = urlBuilder.build().toString()
//
//        val request = Request.Builder()
//                .header(AUTHORIZATION, Url.ACCESS_TOKEN)
//                .url(url)
//                .build()
//
//        httpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(request: Request?, e: IOException?) {
//                    e?.printStackTrace()
//            }
//
//            override fun onResponse(response: Response?) {
//                responseData = response?.body()?.string()
//
//            }
//        })


        val gsonList = ActivityListGsonParser(json).parse() as ActivityListGson

        return activitiesList
    }

    companion object {
        val instance = WebRepository()
    }
}