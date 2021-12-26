package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


interface DogRepository {
    fun getBreedList(isRefresh: Boolean): Flow<List<Breed>?>
    fun getDogImageList(breedId: Int, isRefresh: Boolean = false): Flow<List<DogImage?>>
}

class DefaultDogRepository constructor(
    private val dogApiService: DogApiService
) : DogRepository {
    //Mutex to make writes to cached values thread safe
    private val mutex = Mutex()

    private var breedList: List<Breed>? = emptyList()
    private var dogImageList: List<DogImage> = emptyList()

    override fun getBreedList(isRefresh: Boolean): Flow<List<Breed>?> = flow {
        if (isRefresh || breedList?.isEmpty() == true) {
            val breeds = dogApiService.getBreedsAsync()
            mutex.withLock {
                breedList = breeds?.map { it.toBreedModel() }
            }
        }
        emit(mutex.withLock { breedList })
    }

    override fun getDogImageList(breedId: Int, isRefresh: Boolean) = flow {
        if (isRefresh || dogImageList.isEmpty()) {
            val imageList = dogApiService.getDogImagesAsync(breedId)
            mutex.withLock {
                dogImageList = imageList.map { it.toDogImageModel() }
            }
        }
        emit(mutex.withLock { dogImageList })
    }

}