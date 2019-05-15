package com.shvants.runninglife.loaders

import android.os.AsyncTask

class ActivityListTask(private var json: String) : AsyncTask<Void, Void, String>() {

    override fun onPostExecute(result: String) {
        json = result
    }

    override fun doInBackground(vararg params: Void): String {
        var responseData = ""
//        val httpClient = OkHttpClient()
//
//        val urlBuilder = HttpUrl.parse(StravaHelper.ACTIVITIES_BASE_URL).newBuilder()
////        urlBuilder.addQueryParameter(StravaHelper.BEFORE, StravaHelper.BEFORE_VALUE)
////        urlBuilder.addQueryParameter(StravaHelper.AFTER, StravaHelper.AFTER_VALUE)
////        urlBuilder.addQueryParameter(StravaHelper.PAGE, StravaHelper.PAGE_VALUE)
////        urlBuilder.addQueryParameter(StravaHelper.PER_PAGE, StravaHelper.PER_PAGE_VALUE)
////
////        val url = urlBuilder.build().toString()
//
//        val get = Request.Builder()
//                .header(AUTHORIZATION, StravaHelper.ACCESS_TOKEN)
//                .url(url)
//                .build()
//
//        httpClient.newCall(get).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                responseData = response.get()?.string() ?: ""
//            }
//        })

        return responseData
    }

}