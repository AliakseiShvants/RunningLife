package com.shvants.runninglife.database.fields

import com.shvants.runninglife.utils.Const.REAL

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DbDouble(val name: String = REAL)

