package com.shvants.runninglife.data.db.fields

import com.shvants.runninglife.utils.Const.STRING

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class dbString(val name: String = STRING)