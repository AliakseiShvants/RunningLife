package com.shvants.runninglife.json

import com.google.gson.Gson
import com.shvants.runninglife.model.gson.OauthResponse
import com.shvants.runninglife.strava.StravaHelper
import org.junit.Assert
import org.junit.Test

class GsonParserTest {

    @Test
    fun parseOauthTokenResponse() {
        val oauthTokenResponse = Gson().fromJson(StravaHelper.STUB_TOKEN_RESPONSE, OauthResponse::class.java)

        Assert.assertNotNull(oauthTokenResponse)
        Assert.assertNotNull(oauthTokenResponse.athlete)

        Assert.assertEquals(oauthTokenResponse.accessToken, "5562604c0ffba4017a1838a7d7ae9acfd3b05756")
        Assert.assertEquals(oauthTokenResponse.athlete?.id, 13020790)

    }
}