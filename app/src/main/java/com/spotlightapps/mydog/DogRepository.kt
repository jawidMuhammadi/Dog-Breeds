package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


interface DogRepository {
    suspend fun getBreedList(isRefresh: Boolean): List<Breed>?
    suspend fun getDogImageList(breedId: Int, isRefresh: Boolean = false): List<DogImage?>
}

class DefaultDogRepository constructor(
    private val dogApiService: DogApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DogRepository {
    //Mutex to make writes to cached values thread safe
    private val mutex = Mutex()

    private var breedList: List<Breed>? = emptyList()
    private var dogImageList: List<DogImage> = emptyList()

    override suspend fun getBreedList(isRefresh: Boolean): List<Breed>? {
        withContext(dispatcher) {
            if (isRefresh || breedList?.isEmpty() == true) {
                try {
                    val breeds = dogApiService.getBreedsAsync()
                    mutex.withLock {
                        breedList = breeds?.map { it.toBreedModel() }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return mutex.withLock { breedList }
    }

    override suspend fun getDogImageList(breedId: Int, isRefresh: Boolean): List<DogImage?> {
        withContext(dispatcher) {
            try {
                if (isRefresh || dogImageList.isEmpty()) {
                    val imageList = dogApiService.getDogImagesAsync(breedId)
                    mutex.withLock {
                        dogImageList = imageList.map { it.toDogImageModel() }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return dogImageList
    }

}