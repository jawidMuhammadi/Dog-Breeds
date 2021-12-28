package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.data.api.DogListRemoteDataSource
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


interface DogRepository {
    suspend fun getBreedList(isRefresh: Boolean): Result<List<Breed>?>
    suspend fun getDogImageList(breedId: Int, isRefresh: Boolean = false): Result<List<DogImage?>>
}

class DefaultDogRepository constructor(
    private val remoteDataSource: DogListRemoteDataSource
) : DogRepository {
    //Mutex to make writes to cached values thread safe
    private val mutex = Mutex()

    private var breedList: List<Breed>? = emptyList()
    private var dogImageList: List<DogImage> = emptyList()

    override suspend fun getBreedList(isRefresh: Boolean): Result<List<Breed>?> {
        if (isRefresh || breedList?.isEmpty() == true) {
            try {
                val breeds = remoteDataSource.getBreedList()
                mutex.withLock {
                    breedList = breeds?.map { it.toBreedModel() }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return Result.Error(e)
            }
        }
        return Result.Success(mutex.withLock { breedList })
    }

    override suspend fun getDogImageList(
        breedId: Int,
        isRefresh: Boolean
    ): Result<List<DogImage?>> {
        if (isRefresh || dogImageList.isEmpty()) {
            try {
                val imageList = remoteDataSource.getDogImageList(breedId)
                mutex.withLock {
                    dogImageList = imageList.map { it!!.toDogImageModel() }
                }
            } catch (e: Exception) {
                return Result.Error(e)
            }
        }
        return Result.Success(dogImageList)
    }

}