package com.spotlightapps.mydog

import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage


/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */

class FakeDogRepository : DogRepository {

    private var shouldReturnError = false

    override suspend fun getBreedList(isRefresh: Boolean): Result<List<Breed>?> {
        if (shouldReturnError) return Result.Error(Exception("Network Error"))
        return Result.Success(AndroidTestData.breedList)
    }

    override suspend fun getDogImageList(
        breedId: Int,
        isRefresh: Boolean
    ): Result<List<DogImage?>> {
        if (shouldReturnError) return Result.Error(Exception("Network Error"))
        return Result.Success(AndroidTestData.dogImageList)
    }

    fun setShouldReturnError(isReturnError: Boolean) {
        shouldReturnError = isReturnError
    }

}