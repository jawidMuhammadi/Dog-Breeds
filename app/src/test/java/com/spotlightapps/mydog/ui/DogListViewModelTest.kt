package com.spotlightapps.mydog.ui

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.mydog.TestData
import com.spotlightapps.mydog.FakeDogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */


@ExperimentalCoroutinesApi
class DogListViewModelTest {

    private lateinit var viewModel: DogListViewModel
    private var testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DogListViewModel(FakeDogRepository())
    }

    @Test
    fun dogImageListLoaded() = testDispatcher.runBlockingTest {
        viewModel.getDogsImageList(1)

        val dogImage = viewModel.dogImage.first()

        assertThat(dogImage[0]).isEqualTo(TestData.dogImage1.url)
        assertThat(dogImage[1]).isEqualTo(TestData.dogImage2.url)
    }

    @Test
    fun breedListLoaded() = testDispatcher.runBlockingTest {
        val breedNames = viewModel.breedNames.first()

        assertThat(breedNames?.get(0)).isEqualTo(TestData.dogBreed1.name)
    }

    @Test
    fun loadImageList_loading() = testDispatcher.runBlockingTest {
        testDispatcher.pauseDispatcher()
        //When
        viewModel.getDogsImageList(0)

        //Then
        assertThat(viewModel.uiState.value.isFetchingData).isTrue()

        testDispatcher.resumeDispatcher()
        assertThat(viewModel.uiState.value.isFetchingData).isFalse()
    }

    @Test
    fun loadImageList_returnsError() = testDispatcher.runBlockingTest {
        //Given
        val repository = FakeDogRepository().also {
            it.setShouldReturnError(true)
        }
        viewModel = DogListViewModel(repository)

        //When
        viewModel.getDogsImageList(0)

        //Then
        assertThat(viewModel.uiState.value.errorMessage).isEqualTo("Network Error")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}