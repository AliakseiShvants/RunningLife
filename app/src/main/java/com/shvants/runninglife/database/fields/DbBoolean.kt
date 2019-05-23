package com.shvants.runninglife.database.fields

import com.shvants.runninglife.utils.Const.INTEGER

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DbBoolean(val name: String = INTEGER)