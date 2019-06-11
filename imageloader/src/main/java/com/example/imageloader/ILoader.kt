package com.example.imageloader

import android.widget.ImageView

interface ILoader {

    fun load(imageView: ImageView, uri: String, imageType: ImageType)

    fun clearCache()
}