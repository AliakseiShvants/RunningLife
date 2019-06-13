//package com.shvants.runninglife.loader
//
//import android.content.Context
//import android.util.Log.d
//import androidx.loader.content.AsyncTaskLoader
//import com.shvants.runninglife.strava.StravaHelper
//import com.shvants.runninglife.strava.StravaPreferences
//import com.shvants.runninglife.strava.StravaRequest
//
//class StravaTokenLoader(context: Context) : AsyncTaskLoader<String>(context) {
//
//    private val tag = StravaTokenLoader::class.simpleName
//    private lateinit var jsonString: String
//
//    override fun onStartLoading() {
//        d(tag, "onStartLoading")
//        super.onStartLoading()
//
//        forceLoad()
//    }
//
//    override fun loadInBackground(): String? {
//        d(tag, "loadInBackground")
//        val tokenBody = StravaHelper.getTokenBody(StravaPreferences(context).code)
//        val result = StravaRequest().post(StravaHelper.TOKEN_BASE_URL, tokenBody)
//        //todo ssl exception while post()
//        //SystemClock.sleep(3000)
//        return result
//    }
//
//    override fun onLoadInBackground(): String? {
//        d(tag, "onLoadInBackground")
//        return super.onLoadInBackground()
//    }
//
//    override fun deliverResult(data: String?) {
//        d(tag, "deliverResult")
//        super.deliverResult(data)
//    }
//}