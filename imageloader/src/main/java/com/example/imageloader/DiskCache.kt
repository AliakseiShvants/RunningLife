package com.example.imageloader

import android.graphics.Bitmap
import androidx.annotation.WorkerThread

interface
DiskCache<K, V> {

    @WorkerThread
    fun save(key: K, value: V): Boolean

    @WorkerThread
    fun load(key: K): Bitmap?

    @WorkerThread
    fun clear()
}