package com.spotlightapps.mydog.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.spotlightapps.mydog.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DogListFragmentTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun dogList_basicScreenDisplayed() {
        onView(withId(R.id.textview_first)).check(matches(isDisplayed()))
    }


    @Test
    fun dogList_wrongTitle() {
        onView(withId(R.id.textview_first)).check(matches(withText(R.string.select_dog_breed)))
    }

    @Before
    fun setup() {
        hiltRule.inject()
    }

}