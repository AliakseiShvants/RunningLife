package com.example.imageloader

interface ImageCallback<T> {

    fun onResult(result: T)

    fun onLoadingError()
}