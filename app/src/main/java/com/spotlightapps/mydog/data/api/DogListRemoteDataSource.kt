package com.spotlightapps.mydog.data.api

import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */

interface DogListRemoteDataSource {
    suspend fun getBreedList(): List<BreedApiModel>?
    suspend fun getDogImageList(breedId: Int): List<DogImagesApiModel?>
}

class DogListRemoteDataSourceImp(
    private val dogApiService: DogApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DogListRemoteDataSource {

    override suspend fun getBreedList(): List<BreedApiModel>? {
        return withContext(dispatcher) {
            dogApiService.getBreedsAsync()
        }
    }

    override suspend fun getDogImageList(breedId: Int): List<DogImagesApiModel?> {
        return withContext(dispatcher) {
            dogApiService.getDogImagesAsync(breedId)
        }
    }
}