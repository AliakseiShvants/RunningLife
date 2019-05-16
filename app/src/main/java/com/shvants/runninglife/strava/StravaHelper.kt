package com.shvants.runninglife.strava

import okhttp3.FormBody
import okhttp3.HttpUrl


object StravaHelper {

    fun getAuthorizeUrl(): String {
        val urlBuilder = HttpUrl.parse(AUTHORIZE_BASE_URL)?.newBuilder()

        urlBuilder?.addQueryParameter(CLIENT_ID, CLIENT_ID_VALUE)
        urlBuilder?.addQueryParameter(REDIRECT_URI, REDIRECT_URI_VALUE)
        urlBuilder?.addQueryParameter(RESPONSE_TYPE, RESPONSE_TYPE_VALUE)
        urlBuilder?.addQueryParameter(APPROVAL_PROMPT, APPROVAL_PROMPT_VALUE)
        urlBuilder?.addQueryParameter(SCOPE, SCOPE_VALUE)

        return urlBuilder?.build().toString()
    }

    fun getTokenBody(code: String?): FormBody {
        val tokenBody = FormBody.Builder()
                .add(CLIENT_ID, CLIENT_ID_VALUE)
                .add(CLIENT_SECRET, CLIENT_SECRET_VALUE)
                .add(CODE, code)
                .add(GRANT_TYPE, GRANT_TYPE_VALUE)

        return tokenBody.build()
    }


    private const val ONE_WEEK_IN_SECONDS = 7 * 24 * 60 * 60

    const val EMAIL = "ashvants91@gmail.com"
    const val PASSWORD = "strava11091991"

    const val ACTIVITIES_BASE_URL = "https://www.strava.com/api/v3/athlete/activities"
    private const val AUTHORIZE_BASE_URL = "https://www.strava.com/oauth/mobile/authorize"
    const val TOKEN_BASE_URL = "https://www.strava.com/oauth/token"

    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val AFTER = "after"
    val AFTER_VALUE = (System.currentTimeMillis() / 1000 - ONE_WEEK_IN_SECONDS).toInt().toString()
    private const val APPROVAL_PROMPT = "approval_prompt"
    private const val APPROVAL_PROMPT_VALUE = "auto"
    const val APP_PREFERENCES = "Running_Life"
    const val ATHLETE_ID = "ATHLETE_ID"
    const val AUTHORIZATION = "Authorization"

    const val BEFORE = "before"
    val BEFORE_VALUE = (System.currentTimeMillis() / 1000).toInt().toString()
    val BEARER = "Bearer"

    private const val CLIENT_ID = "client_id"
    private const val CLIENT_ID_VALUE = "34943"
    private const val CLIENT_SECRET = "client_secret"
    private const val CLIENT_SECRET_VALUE = "16ed0e9f2a6c65ecb6c1b5d4d59f18dc266ba0cb"
    const val CODE = "code"

    const val EXPIRES_AT = "EXPIRES_AT"
    const val EXPIRES_IN = "EXPIRES_IN"

    private const val GRANT_TYPE = "grant_type"
    private const val GRANT_TYPE_VALUE = "authorization_code"

    const val PAGE = "page"
    const val PAGE_VALUE = "1"
    const val PER_PAGE = "per_page"
    const val PER_PAGE_VALUE = "7"

    const val REDIRECT_URI = "redirect_uri"
    const val REDIRECT_URI_VALUE = "https://localhost/callback"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
    private const val RESPONSE_TYPE = "response_type"
    private const val RESPONSE_TYPE_VALUE = "code"

    private const val SCOPE = "scope"
    private const val SCOPE_VALUE = "activity:read"

    const val TOKEN_TYPE = "TOKEN_TYPE"

//    var ACCESS_TOKEN = "Bearer 4144d0cb5ea57c2f8487fe2e9f674cf78fd0e6f5"

    val tokenString = "{\n" +
            "    \"token_type\": \"Bearer\",\n" +
            "    \"expires_at\": 1558028670,\n" +
            "    \"expires_in\": 21600,\n" +
            "    \"refresh_token\": \"ea10da071a47981564be28ff70c438764c75f3c8\",\n" +
            "    \"access_token\": \"5562604c0ffba4017a1838a7d7ae9acfd3b05756\",\n" +
            "    \"athlete\": {\n" +
            "        \"id\": 13020790,\n" +
            "        \"username\": \"ashvants\",\n" +
            "        \"resource_state\": 2,\n" +
            "        \"firstname\": \"Aliaksei\",\n" +
            "        \"lastname\": \"Shvants\",\n" +
            "        \"city\": \"Гродно\",\n" +
            "        \"state\": \"Гродненская область\",\n" +
            "        \"country\": \"Беларусь\",\n" +
            "        \"sex\": \"M\",\n" +
            "        \"premium\": false,\n" +
            "        \"summit\": false,\n" +
            "        \"created_at\": \"2016-01-19T08:54:37Z\",\n" +
            "        \"updated_at\": \"2019-05-15T13:36:40Z\",\n" +
            "        \"badge_type_id\": 0,\n" +
            "        \"profile_medium\": \"https://graph.facebook.com/387619144953000/picture?height=256&width=256\",\n" +
            "        \"profile\": \"https://graph.facebook.com/387619144953000/picture?height=256&width=256\",\n" +
            "        \"friend\": null,\n" +
            "        \"follower\": null\n" +
            "    }\n" +
            "}"

}