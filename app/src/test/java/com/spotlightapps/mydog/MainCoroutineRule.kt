package com.spotlightapps.mydog

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by Ahmad Jawid Muhammadi
 * on 02-01-2022.
 */

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher() {

    val testDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        super.finished(description)
    }

}

@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
    this.testDispatcher.runBlockingTest {
        block()
    }
}

fun MainCoroutineRule.coroutineScope() = CoroutineScope(testDispatcher)