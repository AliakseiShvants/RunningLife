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
        return HttpUrl.parse("$ATHLETE_BASE_URL$ACTIVITIES")
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
        val markerStart = "$START_MARKER${start[0]},${start[1]}"
        val markerEnd = "$FINISH_MARKER${end[0]},${end[1]}"
        val height = (width / FI).toInt()
        val size = "$width$X$height"

        return HttpUrl.parse(GOOGLE_STATIC_MAP)
                ?.newBuilder()
                ?.addQueryParameter(PATH, "$ENC$polyline")
                ?.addQueryParameter(KEY, GOOGLE_API_KEY)
                ?.addQueryParameter(MAPTYPE, MAPTYPE_VALUE)
                ?.addQueryParameter(MARKERS, markerStart)
                ?.addQueryParameter(MARKERS, markerEnd)
                ?.addQueryParameter(SIZE, size)
                ?.build()
                .toString()
    }

    fun getKudoersUrl(token: String, id: Long): String {
        return HttpUrl.parse("$ACTIVITIES_BASE_URL$id$KUDOS")
                ?.newBuilder()
                ?.addQueryParameter(ACCESS_TOKEN, token)
                ?.addQueryParameter(PAGE, DEFAULT_PAGE)
                ?.addQueryParameter(PER_PAGE, PER_PAGE_VALUE)
                ?.build()
                .toString()
    }

    fun getActivityUrl(token: String, id: Long): String {
        return HttpUrl.parse("$ACTIVITIES_BASE_URL$id")
                ?.newBuilder()
                ?.addQueryParameter(ACCESS_TOKEN, token)
                ?.addQueryParameter(INCLUDE_ALL_EFFORTS, TRUE)
                ?.build()
                .toString()
    }

    fun getClubsUrl(token: String): String {
        return HttpUrl.parse("$ATHLETE_BASE_URL$CLUBS")
                ?.newBuilder()
                ?.addQueryParameter(ACCESS_TOKEN, token)
                ?.addQueryParameter(PAGE, DEFAULT_PAGE)
                ?.addQueryParameter(PER_PAGE, PER_PAGE_VALUE)
                ?.build()
                .toString()
    }

    fun getClubUrl(token: String, id: Int): String {
        return HttpUrl.parse("$CLUBS_BASE_URL$id")
                ?.newBuilder()
                ?.addQueryParameter(ACCESS_TOKEN, token)
                ?.build()
                .toString()
    }

    fun deleteActivityUrl(id: Long): String {
        return HttpUrl.parse("$ACTIVITIES_BASE_URL$id")
                ?.newBuilder()
                ?.build()
                .toString()
    }

    private const val ONE_YEAR_IN_SECONDS = 365 * 24 * 60 * 60

    private const val ATHLETE_BASE_URL = "https://www.strava.com/api/v3/athlete/"
    private const val ACTIVITIES_BASE_URL = "https://www.strava.com/api/v3/activities/"
    private const val CLUBS_BASE_URL = "https://www.strava.com/api/v3/clubs/"
    private const val AUTHORIZE_BASE_URL = "https://www.strava.com/oauth/mobile/authorize"
    const val LOGIN_URL = "https://www.strava.com/login"
    const val TOKEN_BASE_URL = "https://www.strava.com/oauth/token"

    const val ACCESS_TOKEN = "access_token"
    private const val ACTIVITIES = "activities"
    private const val AFTER = "after"
    private val AFTER_VALUE = (System.currentTimeMillis() / 1000 - ONE_YEAR_IN_SECONDS).toInt().toString()
    private const val APPROVAL_PROMPT = "approval_prompt"
    private const val APPROVAL_PROMPT_VALUE = "auto"
    const val APP_PREFERENCES = "Running_Life"
    const val ATHLETE_ID = "ATHLETE_ID"

    private const val BEFORE = "before"
    private val BEFORE_VALUE = (System.currentTimeMillis() / 1000).toInt().toString()

    private const val CLIENT_ID = "client_id"
    private const val CLIENT_ID_VALUE = "34943"
    private const val CLIENT_SECRET = "client_secret"
    private const val CLIENT_SECRET_VALUE = "16ed0e9f2a6c65ecb6c1b5d4d59f18dc266ba0cb"
    private const val CLUBS = "/clubs"
    const val CODE = "code"

    private const val DEFAULT_PAGE = "1"

    private const val ENC = "enc:"
    const val EXPIRES_AT = "expires_at"
    const val EXPIRES_IN = "expiresIn"

    private const val FALSE = "false"
    private const val FI = 1.6
    private const val FINISH_MARKER = "color:red|label:F|"

    private const val GOOGLE_API_KEY = "AIzaSyDXvElA5LGkMVBeMGtn9NMKax3PvnelKXM"
    private const val GOOGLE_STATIC_MAP = "https://maps.googleapis.com/maps/api/staticmap"
    private const val GRANT_TYPE = "grant_type"
    private const val GRANT_TYPE_VALUE = "authorization_code"

    private const val INCLUDE_ALL_EFFORTS = "include_all_efforts"

    private const val KEY = "key"
    private const val KUDOS = "/kudos"

    private const val MAPTYPE = "maptype"
    private const val MAPTYPE_VALUE = "roadmap"
    private const val MARKERS = "markers"

    private const val PAGE = "page"
    private const val PAGE_VALUE = "1"
    private const val PATH = "path"
    private const val PER_PAGE = "per_page"
    private const val PER_PAGE_VALUE = "20"

    private const val REDIRECT_URI = "redirect_uri"
    const val REDIRECT_URI_VALUE = "https://localhost/callback"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
    private const val RESPONSE_TYPE = "response_type"
    private const val RESPONSE_TYPE_VALUE = "code"

    private const val SIZE = "size"
    private const val SCOPE = "scope"
    private const val SCOPE_VALUE = "activity:read"
    private const val START_MARKER = "color:green|label:S|"

    const val TOKEN_TYPE = "TOKEN_TYPE"
    private const val TRUE = "true"

    private const val X = "x"
}