package com.shvants.runninglife.utils.service

import com.shvants.runninglife.utils.ICallback

interface IService<T> {

    fun getEntity(position: Int): T

    fun getEntities(): List<T>

    fun getEntities(startRange: Int, endRange: Int, callback: ICallback<List<T>>)

    fun size(): Int
}
