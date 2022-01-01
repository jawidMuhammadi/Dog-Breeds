package com.spotlightapps.mydog.domain

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.mydog.FakeDogRepository
import com.spotlightapps.mydog.MainCoroutineRule
import com.spotlightapps.mydog.Result
import com.spotlightapps.mydog.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 02-01-2022.
 */


@ExperimentalCoroutinesApi
class DogImageRequestTest {

    @get: Rule
    val rule = MainCoroutineRule()


    @Test
    fun loadDogImageList() = rule.testDispatcher.runBlockingTest {
        //Given
        val loadDogImagesUseCase = LoadDogImagesUseCase(
            FakeDogRepository(), rule.testDispatcher
        )
        //When
        val imageList = loadDogImagesUseCase(DogImageRequest(true, 1))

        //Then
        assertThat((imageList as Result.Success).data).isEqualTo(TestData.dogImageList)
    }
}