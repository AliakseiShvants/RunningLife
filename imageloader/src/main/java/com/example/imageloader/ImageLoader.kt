package com.example.imageloader

import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.collection.LruCache
import okhttp3.*
import java.io.IOException
import java.util.concurrent.Executors

open class ImageLoader private constructor() : ILoader {

    private val httpClient = OkHttpClient()
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler(Looper.getMainLooper())
    private val diskCache: DiskCache<String, Bitmap> = BitmapDiskCache()

    private val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
    private val cacheSize = maxMemory / EIGHT

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / ONE_KB
        }
    }

    override fun load(imageView: ImageView, uri: String, imageType: ImageType, isGone: Boolean) {
        if (uri == Const.EMPTY) {
            if (isGone) goneEmptyImage(imageView)

            return
        }

        imageView.tag = uri

        loadFromMemoryCache(uri, object : ImageCallback<Bitmap> {

            override fun onResult(result: Bitmap) {
                setImage(imageView, uri, result, imageType)
            }

            override fun onLoadingError() {
                executor.execute {
                    loadFromDiskCache(uri, object : ImageCallback<Bitmap> {

                        override fun onResult(result: Bitmap) {
                            setImage(imageView, uri, result, imageType)
                        }

                        override fun onLoadingError() {
                            loadFromNetwork(uri, object : ImageCallback<Bitmap> {

                                override fun onResult(result: Bitmap) {
                                    setImage(imageView, uri, result, imageType)
                                }

                                override fun onLoadingError() {
                                    if (isGone) goneEmptyImage(imageView)
                                }
                            })
                        }
                    })
                }
            }
        })
    }

    private fun goneEmptyImage(imageView: ImageView) {
        handler.post { imageView.visibility = View.GONE }
    }

    private fun isImageShouldBeSet(imageView: ImageView, uri: String): Boolean {
        return uri == imageView.tag
    }

    private fun loadFromMemoryCache(uri: String, callback: ImageCallback<Bitmap>) {
        synchronized(memoryCache) {
            val bitmap: Bitmap? = memoryCache.get(uri)

            if (bitmap == null) {
                callback.onLoadingError()
            } else {
                callback.onResult(bitmap)
            }
        }
    }

    private fun setImage(imageView: ImageView, uri: String, bitmap: Bitmap, imageType: ImageType) {
        val result = cropImage(bitmap, imageType)

        if (isImageShouldBeSet(imageView, uri)) {
            handler.post {
                imageView.setImageBitmap(result)
            }
        }
    }

    private fun cropImage(bitmap: Bitmap, imageType: ImageType): Bitmap {
        return when (imageType) {
            ImageType.ROUNDED -> getCircularBitmap(bitmap)
            else -> bitmap
        }
    }

    private fun getCircularBitmap(bitmap: Bitmap): Bitmap {

        val output: Bitmap =
                if (bitmap.width > bitmap.height) {
                    Bitmap.createBitmap(bitmap.height, bitmap.height, Bitmap.Config.ARGB_8888)
                } else {
                    Bitmap.createBitmap(bitmap.width, bitmap.width, Bitmap.Config.ARGB_8888)
                }

        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)

        val r: Float =
                if (bitmap.width > bitmap.height) {
                    (bitmap.height / 2).toFloat()
                } else {
                    (bitmap.width / 2).toFloat()
                }

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(r, r, r, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }

    private fun loadFromDiskCache(uri: String, callback: ImageCallback<Bitmap>) {
        synchronized(diskCache) {
            val bitmap: Bitmap? = diskCache.load(uri)

            if (bitmap == null) {
                callback.onLoadingError()
            } else {
                callback.onResult(bitmap)
            }
        }
    }

    private fun loadFromNetwork(uri: String, callback: ImageCallback<Bitmap>) {
        val request = Request.Builder()
                .url(uri)
                .build()

        httpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callback.onLoadingError()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = decodeImage(response)

                callback.onResult(result)
                putInMemoryCache(uri, result)
                putInDiskCache(uri, result)
            }
        })
    }

    private fun decodeImage(response: Response): Bitmap =
            BitmapFactory.decodeStream(response.body()?.byteStream())

    private fun putInMemoryCache(uri: String, image: Bitmap) {
        synchronized(memoryCache) {
            memoryCache.put(uri, image)
        }
    }

    private fun putInDiskCache(uri: String, image: Bitmap) {
        synchronized(diskCache) {
            diskCache.save(uri, image)
        }
    }

    override fun clearCache() {
        executor.execute {
            synchronized(memoryCache) {
                memoryCache.evictAll()
            }

            synchronized(diskCache) {
                diskCache.clear()
            }
        }
    }

    companion object {
        fun getInstance() = ImageLoader()
        private const val ONE_KB = 1024
        private const val EIGHT = 8
    }
}