package com.example.imageloader

import android.graphics.Bitmap
import android.widget.ImageView

interface ILoader {

    fun load(images: ArrayList<Bitmap>, uri: String, imageType: ImageType)

    fun loadAndSet(imageView: ImageView, uri: String, imageType: ImageType, isGone: Boolean = true)

    fun clearCache()
}