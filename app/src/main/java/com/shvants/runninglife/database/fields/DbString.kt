package com.shvants.runninglife.database.fields

import com.shvants.runninglife.utils.Const.STRING

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DbString(val name: String = STRING)