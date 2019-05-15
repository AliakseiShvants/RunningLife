package com.shvants.runninglife.utils

interface ICallback<T> {

    fun onResult(result: T)

    fun onError(message: String)
}
