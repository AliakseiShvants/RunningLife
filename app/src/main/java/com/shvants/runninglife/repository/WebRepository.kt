package com.shvants.runninglife.repository

import android.os.AsyncTask
import com.google.gson.Gson
import com.shvants.runninglife.json.parser.ActivityListJsonParser
import com.shvants.runninglife.loader.ActivityListTask
import com.shvants.runninglife.model.base.MetaAthlete
import com.shvants.runninglife.model.gson.ActivityListGson
import com.shvants.runninglife.model.gson.SummaryActivityGson
import com.shvants.runninglife.model.gson.SummaryAthleteGson
import com.shvants.runninglife.strava.StravaRequest

class WebRepository {

    fun getAthleteActivities(token: String): List<SummaryActivityGson> {
        //todo handle if token empty
        val json = StravaRequest().makeAthleteActivitiesRequest(token)
        val activitiesArr = Gson().fromJson(json, Array<SummaryActivityGson>::class.java)

        return ActivityListGson(activitiesArr.toList()).getList()
    }

    fun getAthlete(id: Int?): MetaAthlete {


        return SummaryAthleteGson.Builder()
                .id(1)
                .firstName("Aliaksei")
                .lastName("Shvants")
                .city("Grodno")
                .build()
    }

//    private val preferences : StravaPreferences? = context?.let { StravaPreferences(it) }

//    fun getAthlete(): SummaryAthleteGson {
//        val id = preferences?.athleteId
//        val athlete =
//                if (id != null) {
//                    loadAthlete(id)
//                } else {
//
//                }
//
//
//        return SummaryAthleteGson.Builder()
//                .id(1)
//                .firstName("Aliaksei")
//                .lastName("Shvants")
//                .city("Grodno")
//                .build()
//    }


    fun getAthleteActivities(id: Int): List<SummaryActivityGson> {
        val activitiesList = ArrayList<SummaryActivityGson>()
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


}