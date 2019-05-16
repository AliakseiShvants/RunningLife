package com.shvants.runninglife.strava

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

        //            val httpClient = HttpClient.getUnsafeOkHttpClient()

//        val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)
//                .build()
//
//        val httpClient = OkHttpClient.Builder()
//                .connectionSpecs(Collections.singletonList(spec))
//                .build()

        val httpClient = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        val response = httpClient
                .newCall(request)
                .execute()
//                .enqueue()
        result = response.body()?.string() ?: Const.EMPTY

        return result
//        val handler = Handler()

        /*Thread(Runnable {
//            val httpClient = HttpClient.getUnsafeOkHttpClient()
            val httpClient = OkHttpClient()
            val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

            val response = httpClient
                    .newCall(request)
                    .execute()

//            handler.post {  }
            result = response.body()?.string() ?: Const.EMPTY
        }).start()

        return result*/
    }
}