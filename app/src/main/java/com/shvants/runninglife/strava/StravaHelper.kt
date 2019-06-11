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

    fun getAthleteActivitiesUrl(token: String, page: Int): String {
        return HttpUrl.parse(ACTIVITIES_BASE_URL)
                ?.newBuilder()
                ?.addQueryParameter(ACCESS_TOKEN, token)
                ?.addQueryParameter(BEFORE, BEFORE_VALUE)
                ?.addQueryParameter(AFTER, AFTER_VALUE)
                ?.addQueryParameter(PAGE, "$page")
                ?.addQueryParameter(PER_PAGE, PER_PAGE_VALUE)
                ?.build()
                .toString()
    }

    fun getTokenBody(code: String): FormBody {
        return FormBody.Builder()
                .add(CLIENT_ID, CLIENT_ID_VALUE)
                .add(CLIENT_SECRET, CLIENT_SECRET_VALUE)
                .add(CODE, code)
                .add(GRANT_TYPE, GRANT_TYPE_VALUE)
                .build()
    }

    fun getActivitiesBody(): FormBody {
        return FormBody.Builder()
                .add(BEFORE, BEFORE_VALUE)
                .add(AFTER, AFTER_VALUE)
                .add(PAGE, PAGE_VALUE)
                .add(PER_PAGE, PER_PAGE_VALUE)
                .build()
    }

    fun getActivityMapUrl(polyline: String, start: FloatArray, end: FloatArray, width: Int): String {
        val markerStart = "color:green|label:S|${start[0]},${start[1]}"
        val markerEnd = "color:red|label:F|${end[0]},${end[1]}"
        val height = (width / 1.6).toInt()
        val size = "${width}x$height"

        return HttpUrl.parse(GOOGLE_STATIC_MAP)
                ?.newBuilder()
                ?.addQueryParameter(PATH, "enc:$polyline")
                ?.addQueryParameter(KEY, GOOGLE_API_KEY)
                ?.addQueryParameter(MAPTYPE, MAPTYPE_VALUE)
                ?.addQueryParameter(MARKERS, markerStart)
                ?.addQueryParameter(MARKERS, markerEnd)
                ?.addQueryParameter(SIZE, size)
                ?.build()
                .toString()
    }

    private const val GOOGLE_STATIC_MAP = "https://maps.googleapis.com/maps/api/staticmap"
    private const val PATH = "path"
    private const val KEY = "key"
    private const val GOOGLE_API_KEY = "AIzaSyDXvElA5LGkMVBeMGtn9NMKax3PvnelKXM"
    private const val MAPTYPE = "maptype"
    private const val MAPTYPE_VALUE = "roadmap"
    private const val MARKERS = "markers"
    private const val SIZE = "size"

    private const val ONE_YEAR_IN_SECONDS = 365 * 24 * 60 * 60

    const val EMAIL = "ashvants91@gmail.com"
    const val PASSWORD = "strava11091991"

    const val ATHLETE_BASE_URL = "https://www.strava.com/api/v3/athlete"
    const val ACTIVITIES_BASE_URL = "https://www.strava.com/api/v3/athlete/activities"
    private const val AUTHORIZE_BASE_URL = "https://www.strava.com/oauth/mobile/authorize"
    const val LOGIN_URL = "https://www.strava.com/login"
    const val TOKEN_BASE_URL = "https://www.strava.com/oauth/token"

    const val ACCESS_TOKEN = "access_token"
    const val AFTER = "after"
    val AFTER_VALUE = (System.currentTimeMillis() / 1000 - ONE_YEAR_IN_SECONDS).toInt().toString()
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

    const val EXPIRES_AT = "expires_at"
    const val EXPIRES_IN = "expires_in"

    private const val GRANT_TYPE = "grant_type"
    private const val GRANT_TYPE_VALUE = "authorization_code"

    const val PAGE = "page"
    const val PAGE_VALUE = "1"
    const val PER_PAGE = "per_page"
    const val PER_PAGE_VALUE = "20"

    const val REDIRECT_URI = "redirect_uri"
    const val REDIRECT_URI_VALUE = "https://localhost/callback"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
    private const val RESPONSE_TYPE = "response_type"
    private const val RESPONSE_TYPE_VALUE = "code"

    private const val SCOPE = "scope"
    private const val SCOPE_VALUE = "activity:read"

    const val TOKEN_TYPE = "TOKEN_TYPE"

//    var ACCESS_TOKEN = "Bearer 4144d0cb5ea57c2f8487fe2e9f674cf78fd0e6f5"

    const val STUB_TOKEN_RESPONSE = "{\n" +
            "    \"token_type\": \"Bearer\",\n" +
            "    \"expires_at\": 1558439307,\n" +
            "    \"expires_in\": 9935,\n" +
            "    \"refresh_token\": \"ea10da071a47981564be28ff70c438764c75f3c8\",\n" +
            "    \"access_token\": \"0e6c0137f33305f55065927dfa2ea6203f8588e4\",\n" +
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
            "        \"updated_at\": \"2019-05-20T16:55:29Z\",\n" +
            "        \"badge_type_id\": 0,\n" +
            "        \"profile_medium\": \"https://graph.facebook.com/387619144953000/picture?height=256&width=256\",\n" +
            "        \"profile\": \"https://graph.facebook.com/387619144953000/picture?height=256&width=256\",\n" +
            "        \"friend\": null,\n" +
            "        \"follower\": null\n" +
            "    }\n" +
            "}"
}