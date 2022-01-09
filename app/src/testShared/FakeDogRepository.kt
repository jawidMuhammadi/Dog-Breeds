package com.spotlightapps.mydog

import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */

class FakeDogRepository : DogRepository {

    private var shouldReturnError = false

    override suspend fun getBreedList(isRefresh: Boolean): List<Breed> {
        if(shouldReturnError) throw Exception("Test API call failed")
        return TestData.breedList
    }

    override suspend fun getDogImageList(
        breedId: Int,
        isRefresh: Boolean
    ): List<DogImage?> {
        return (TestData.dogImageList)
    }

    fun setShouldReturnError(isReturnError: Boolean) {
        shouldReturnError = isReturnError
    }

}