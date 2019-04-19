package com.shvants.runninglife.database

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Table(val name: String)