package com.spotlightapps.mydog

import com.spotlightapps.mydog.data.remote.ApiManager
import com.spotlightapps.mydog.model.breed.Breed
import com.spotlightapps.mydog.model.dogimage.DogImages
import kotlinx.coroutines.Deferred
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */

class AppRepository @Inject constructor(
    private val apiManager: ApiManager
) {
    fun getBreedList(isToForceRemote: Boolean = true): Deferred<List<Breed>?> {
        return if (isToForceRemote) {
            apiManager.appApiServices.getBreedsAsync()
        } else TODO("implement offline feature logic")
    }

    fun getDogImageList(breedId: Int, isToForceRemote: Boolean = true): Deferred<DogImages> {
        return if (isToForceRemote) {
            apiManager.appApiServices.getDogImagesAsync(breedId)
        } else TODO("implement offline feature logic")
    }

}