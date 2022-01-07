package com.spotlightapps.mydog.ui

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.mydog.FakeDogRepository
import com.spotlightapps.mydog.MainCoroutineRule
import com.spotlightapps.mydog.TestData
import com.spotlightapps.mydog.domain.LoadBreedListUseCase
import com.spotlightapps.mydog.domain.LoadDogImagesUseCase
import com.spotlightapps.mydog.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */


@ExperimentalCoroutinesApi
class DogListViewModelTest {

    private lateinit var viewModel: DogListViewModel
    private var repository = FakeDogRepository()

    @get: Rule
    val rule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = DogListViewModel(
            LoadDogImagesUseCase(repository, rule.testDispatcher),
            LoadBreedListUseCase(repository, rule.testDispatcher)
        )
    }

    @Test
    fun dogImageListLoaded() = rule.runBlockingTest {
        viewModel.getDogsImageList(1)

        val dogImage = viewModel.dogImage.first()

        assertThat(dogImage[0]).isEqualTo(TestData.dogImage1.url)
        assertThat(dogImage[1]).isEqualTo(TestData.dogImage2.url)
    }

    @Test
    fun breedListLoaded() = rule.runBlockingTest {
        val breedNames = viewModel.breedNames.first()

        assertThat(breedNames?.get(0)).isEqualTo(TestData.dogBreed1.name)
    }

    @Test
    fun loadImageList_loading() = rule.testDispatcher.runBlockingTest {
        rule.testDispatcher.pauseDispatcher()
        //When
        viewModel.getDogsImageList(0)

        //Then
        assertThat(viewModel.uiState.value.isFetchingData).isTrue()

        rule.testDispatcher.resumeDispatcher()
        assertThat(viewModel.uiState.value.isFetchingData).isFalse()
    }

    @Test
    fun loadImageList_returnsError() = rule.testDispatcher.runBlockingTest {
        //Given
        repository.apply {
            setShouldReturnError(true)
        }

        viewModel = DogListViewModel(
            LoadDogImagesUseCase(repository, rule.testDispatcher),
            LoadBreedListUseCase(repository, rule.testDispatcher)
        )

        //When
        viewModel.getDogsImageList(0)

        //Then
        assertThat(viewModel.uiState.value.errorMessage).isEqualTo("Network Error")
    }

}