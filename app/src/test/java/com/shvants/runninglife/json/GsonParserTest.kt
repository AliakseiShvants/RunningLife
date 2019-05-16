package com.shvants.runninglife.json

import com.google.gson.Gson
import com.shvants.runninglife.data.web.model.ActivityListGson
import com.shvants.runninglife.data.web.model.OauthResponse
import com.shvants.runninglife.http.JsonReader
import com.shvants.runninglife.json.parser.ActivityListJsonParser
import com.shvants.runninglife.mocks.Mocks
import com.shvants.runninglife.strava.StravaHelper
import org.junit.Assert
import org.junit.Test

class GsonParserTest {

    @Test
    fun parseActivityList() {
        val mockedInputStream = Mocks().stream("summitActivitiesList.json")
        val jsonString = JsonReader(mockedInputStream).read()
        val parser = ActivityListJsonParser(jsonString)
        val list = parser.parse() as ActivityListGson

        Assert.assertNotNull(list)
        Assert.assertNotNull(list.getList())
        Assert.assertEquals(EXPECTED_SIZE, list.getList().size)
    }

    @Test
    fun parseOauthTokenResponse() {
        val oauthTokenResponse = Gson().fromJson(StravaHelper.tokenString, OauthResponse::class.java)

        Assert.assertNotNull(oauthTokenResponse)
        Assert.assertNotNull(oauthTokenResponse.athlete)

        Assert.assertEquals(oauthTokenResponse.accessToken, "5562604c0ffba4017a1838a7d7ae9acfd3b05756")
        Assert.assertEquals(oauthTokenResponse.athlete?.id, 13020790)

    }

    companion object {
        private const val EXPECTED_SIZE = 10
    }
}