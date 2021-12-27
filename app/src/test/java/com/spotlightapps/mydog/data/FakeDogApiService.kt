package com.spotlightapps.mydog.data

import com.spotlightapps.mydog.TestData
import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel

/**
 * Created by Ahmad Jawid Muhammadi
 * on 27-12-2021.
 */

class FakeDogApiService : DogApiService {
    override suspend fun getBreedsAsync(): List<BreedApiModel>? {
        return TestData.breedApiModelList
    }

    override suspend fun getDogImagesAsync(breedId: Int, limit: Int): List<DogImagesApiModel> {
        return TestData.dogImageApiModelList
    }
}