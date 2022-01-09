package com.spotlightapps.mydog.ui

import com.spotlightapps.mydog.di.launchFragmentInHiltContainer
import com.spotlightapps.mydog.ui.list.DogListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-01-2022.
 */

@HiltAndroidTest
class DogListScreenTest {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun launch() {
        hiltRule.inject()
        launchFragmentInHiltContainer<DogListFragment>()
    }

    @Test
    fun basicScreenTest() {

    }
}