package com.project.mymarvel.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.mymarvel.data.server.fromJson
import com.project.mymarvel.rules.MockWebServerRule
import com.project.mymarvel.ui.base.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import com.project.mymarvel.R
import com.project.mymarvel.data.server.enqueueMany

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainInstrumentationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServerRule.server.enqueueMany(
            MockResponse().fromJson("characters.json"),
            MockResponse().fromJson("events.json")
        )

    }

    @Test
    fun refresh_events_when_scroll_on_marvel_items() {
        onView(withId(R.id.main_recycler))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))

        onView(withId(R.id.event_recycler))
            .check(matches(hasDescendant(withText("Civil War"))))
    }
}