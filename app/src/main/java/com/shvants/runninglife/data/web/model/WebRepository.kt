package com.shvants.runninglife.data.web.model

import android.os.AsyncTask
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.json.parser.ActivityListJsonParser
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

        Thread(Runnable {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }).start()
//        val  task = JavaAsync()
//        task.execute()


//        task.cancel(false)


//        var responseData: String? = ""
//        val httpClient = OkHttpClient()
//
//        val urlBuilder = HttpUrl.parse(StravaHelper.ACTIVITIES_BASE_URL).newBuilder()
//        urlBuilder.addQueryParameter(StravaHelper.BEFORE, StravaHelper.BEFORE_VALUE)
//        urlBuilder.addQueryParameter(StravaHelper.AFTER, StravaHelper.AFTER_VALUE)
//        urlBuilder.addQueryParameter(StravaHelper.PAGE, StravaHelper.PAGE_VALUE)
//        urlBuilder.addQueryParameter(StravaHelper.PER_PAGE, StravaHelper.PER_PAGE_VALUE)
//
//        val url = urlBuilder.build().toString()
//
//        val get = Request.Builder()
//                .header(AUTHORIZATION, StravaHelper.ACCESS_TOKEN)
//                .url(url)
//                .build()
//
//        httpClient.newCall(get).enqueue(object : Callback {
//            override fun onFailure(get: Request?, e: IOException?) {
//                    e?.printStackTrace()
//            }
//
//            override fun onResponse(response: Response?) {
//                responseData = response?.get()?.string()
//
//            }
//        })


        val gsonList = ActivityListJsonParser(json).parse() as ActivityListGson

        return activitiesList
    }

    companion object {
        val instance = WebRepository()
    }
}