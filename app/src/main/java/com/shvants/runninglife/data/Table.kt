package com.shvants.runninglife.data

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Table(val name: String)