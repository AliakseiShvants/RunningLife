package com.shvants.runninglife.repository

import android.content.ContentValues
import android.content.Context
import android.os.Build.VERSION_CODES.P
import androidx.test.core.app.ApplicationProvider
import com.shvants.runninglife.RunningLifeApp
import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.DbHelper
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [P], packageName = "com.shvants.runninglife")
class DbRepositoryTest {

    private lateinit var context: Context
    private lateinit var dbHelper: DbHelper

    private val id = 13020790

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<RunningLifeApp>()
        dbHelper = DbHelper(context)
    }

    @Test
    fun insertAthlete() {
        val contentValues = ContentValues().apply {
            put(Contract.ID, id)
            put(Contract.FULLNAME, "Aliaksei Shvants")
            put(Contract.PROFILE, "https://graph.facebook.com/387619144953000/picture?height=256&width=256")
            put(Contract.PROFILE_MEDIUM, "https://graph.facebook.com/387619144953000/picture?height=256&width=256")
            put(Contract.AthleteEntry.LOCATION, "grodno")
            put(Contract.SEX, "M")
            put(Contract.SUMMIT, 0)
        }

        val rowId = dbHelper.insert(Contract.TABLE_NAME, contentValues)

        Assert.assertTrue(rowId != -1L)
    }

    @Test
    fun getAthlete() {
        insertAthlete()

        val athleteUi = SummaryAthleteUi("", "", "")
        dbHelper.query("SELECT * FROM ${Contract.AthleteEntry.TABLE_NAME} WHERE _ID = $id")
                .use { cursor ->
                    if (cursor.moveToFirst()) {
                        val profile = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.PROFILE))
                        val fullname = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.FULLNAME))
                        val location = cursor.getString(cursor.getColumnIndex(Contract.AthleteEntry.LOCATION))
                        athleteUi.profile = profile
                        athleteUi.fullName = fullname
                        athleteUi.location = location
                    }
                }

        Assert.assertEquals(athleteUi.fullName, "Aliaksei Shvants")
    }


    @After
    fun drop() {
        context.deleteDatabase(dbHelper.databaseName)
    }
}