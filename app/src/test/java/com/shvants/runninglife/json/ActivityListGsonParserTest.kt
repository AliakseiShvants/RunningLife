package com.shvants.runninglife.json

import com.shvants.runninglife.http.JsonReader
import com.shvants.runninglife.json.parser.ActivityListJsonParser
import com.shvants.runninglife.mocks.Mocks
import org.junit.Assert
import org.junit.Test

class ActivityListGsonParserTest {

    @Test
    fun parse() {
        val mockedInputStream = Mocks().stream("summitActivitiesList.json")
        val jsonString = JsonReader(mockedInputStream).read()
        val parser = ActivityListJsonParser(jsonString)
        val list = parser.parse() as ActivityListGson

        Assert.assertNotNull(list)
        Assert.assertNotNull(list.getList())
        Assert.assertEquals(EXPECTED_SIZE, list.getList().size)
    }

    companion object {
        private const val EXPECTED_SIZE = 10
    }
}