package com.shvants.runninglife.repository

import org.junit.Assert
import org.junit.Test

class WebRepositoryTest {

    @Test
    fun athlete_activities_is_correct() {
        val token = "1430afb96be60bf79bf9ce4a1031399b932a5e76"
        val list = WebRepository().getAthleteActivities(token)

        Assert.assertNotNull(list)
    }
}