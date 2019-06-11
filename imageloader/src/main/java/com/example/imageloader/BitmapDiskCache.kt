package com.example.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BitmapDiskCache : DiskCache<String, Bitmap> {

    private val diskCachePath = Environment.getExternalStorageDirectory().toString()

    override fun save(key: String, value: Bitmap): Boolean {
        return try {
            val file = File(diskCachePath, Uri.parse(key).lastPathSegment)
            val stream = FileOutputStream(file)

            value.compress(Bitmap.CompressFormat.JPEG, BITMAP_QUALITY, stream)
            stream.flush()
            stream.close()

            true
        } catch (e: IOException) {
            false
        }
    }

    override fun load(key: String): Bitmap? =
            BitmapFactory.decodeFile(File(diskCachePath, Uri.parse(key).lastPathSegment).toString())

    override fun clear() {
    }

    companion object {
        private const val BITMAP_QUALITY = 50
    }
}