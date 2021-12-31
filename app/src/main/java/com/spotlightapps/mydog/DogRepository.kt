package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


interface DogRepository {
    suspend fun getBreedList(isRefresh: Boolean): List<Breed>?
    suspend fun getDogImageList(breedId: Int, isRefresh: Boolean = false): Result<List<DogImage?>>
}

class DefaultDogRepository constructor(
    private val remoteDataSource: DogApiService
) : DogRepository {
    //Mutex to make writes to cached values thread safe
    private val mutex = Mutex()

    private var breedList: List<Breed>? = emptyList()
    private var dogImageList: List<DogImage> = emptyList()

    override suspend fun getBreedList(isRefresh: Boolean): List<Breed>? {
        if (isRefresh || breedList?.isEmpty() == true) {
            val breeds = remoteDataSource.getBreedsAsync()
            mutex.withLock {
                breedList = breeds?.map { it.toBreedModel() }

            }
        }
        return mutex.withLock { breedList }

    }

    override suspend fun getDogImageList(
        breedId: Int,
        isRefresh: Boolean
    ): Result<List<DogImage?>> {
        if (isRefresh || dogImageList.isEmpty()) {
            try {
                val imageList = remoteDataSource.getDogImagesAsync(breedId)
                mutex.withLock {
                    dogImageList = imageList.map { it.toDogImageModel() }
                }
            } catch (e: Exception) {
                return Result.Error(e)
            }
        }
        return Result.Success(dogImageList)
    }

}