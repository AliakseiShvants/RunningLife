package com.example.imageloader

import android.widget.ImageView

interface ILoader {

    fun load(imageView: ImageView, uri: String, imageType: ImageType, isGone: Boolean = true)

    fun clearCache()
}