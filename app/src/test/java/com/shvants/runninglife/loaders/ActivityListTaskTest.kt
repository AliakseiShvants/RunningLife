package com.shvants.runninglife.loaders


import com.shvants.runninglife.data.web.model.WebRepository
import org.junit.Test

class ActivityListTaskTest {

//    @Mock
//    val context: Context

    @Test
    fun load() {
        val repository = WebRepository.instance
        repository.getAthleteSummaryActivities(1)

    }
}