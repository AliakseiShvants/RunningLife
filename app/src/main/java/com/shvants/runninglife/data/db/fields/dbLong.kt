package com.shvants.runninglife.data.db.fields

import com.shvants.runninglife.utils.Const.INTEGER

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class dbLong(val name: String = INTEGER)