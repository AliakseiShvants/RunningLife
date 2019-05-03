package com.shvants.runninglife.data.db

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Table(val name: String)