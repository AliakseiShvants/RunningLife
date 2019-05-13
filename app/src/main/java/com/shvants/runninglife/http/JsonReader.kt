package com.shvants.runninglife.http

import java.io.IOException
import java.io.InputStream

class JsonReader(private val inputStream: InputStream) {

    fun read(): String {
        var result: String = ""

        try {
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            result = String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}