package com.spotlightapps.mydog.data

import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.Result
import com.spotlightapps.mydog.TestData
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import java.lang.Exception

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */

class FakeDogRepository : DogRepository {

    private var shouldReturnError = false

    override suspend fun getBreedList(isRefresh: Boolean): Result<List<Breed>?> {
        if (shouldReturnError) return Result.Error(Exception("Network Error"))
        return Result.Success(TestData.breedList)
    }

    override suspend fun getDogImageList(
        breedId: Int,
        isRefresh: Boolean
    ): Result<List<DogImage?>> {
        if (shouldReturnError) return Result.Error(Exception("Network Error"))
        return Result.Success(TestData.dogImageList)
    }

    fun setShouldReturnError(isReturnError: Boolean) {
        shouldReturnError = isReturnError
    }

}