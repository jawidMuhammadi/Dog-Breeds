package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.remote.ApiManager
import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */

class AppRepository @Inject constructor(
    private val apiManager: ApiManager
) {
    //Mutex to make writes to cached value thread safe
    private val mutex = Mutex()

    private var breedList: List<Breed>? = emptyList()
    private var dogImageList: List<DogImage> = emptyList()

    fun getBreedList(isRefresh: Boolean = false) = flow {
        if (isRefresh || breedList?.isEmpty() == true) {
            val breeds = apiManager.appApiServices.getBreedsAsync()
            mutex.withLock {
                breedList = breeds?.map { it.toBreedModel() }
            }
        }
        emit(mutex.withLock { breedList })
    }

    fun getDogImageList(breedId: Int, isRefresh: Boolean = false) = flow {
        if (isRefresh || dogImageList.isEmpty()) {
            val imageList = apiManager.appApiServices.getDogImagesAsync(breedId)
            mutex.withLock {
                dogImageList = imageList.map { it.toDogImageModel() }
            }
        }
        emit(mutex.withLock { dogImageList })
    }

}