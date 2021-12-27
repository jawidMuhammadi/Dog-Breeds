package com.spotlightapps.mydog

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.mydog.data.FakeDogApiService
import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel
import com.spotlightapps.mydog.ui.DogListViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 27-12-2021.
 */


@ExperimentalCoroutinesApi
class DogRepositoryTest {

    @Test
    fun loadBreedList() = runBlockingTest {
        val dogRepository = DefaultDogRepository(FakeDogApiService(), TestCoroutineDispatcher())

        val breedList = dogRepository.getBreedList(true)

        assertThat(breedList?.get(0)).isEqualTo(TestData.dogBreed1)
    }
}
