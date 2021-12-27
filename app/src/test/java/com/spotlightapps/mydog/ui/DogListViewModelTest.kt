package com.spotlightapps.mydog.ui

import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.TestData
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */

class DogListViewModelTest : TestCase() {

    private lateinit var viewModel: DogListViewModel

    override fun setUp() {
        viewModel = DogListViewModel(FakeDogRepository())
    }

    internal class FakeDogRepository : DogRepository {
        override suspend fun getBreedList(isRefresh: Boolean): List<Breed> {
            return TestData.breedList
        }

        override suspend fun getDogImageList(breedId: Int, isRefresh: Boolean): List<DogImage?> {
            return TestData.dogImageList
        }
    }
}