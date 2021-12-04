package com.spotlightapps.mydog.data.remote

import com.spotlightapps.mydog.model.breed.Breed
import com.spotlightapps.mydog.model.dogimage.DogImages
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-10-2021.
 */

interface AppApiServices {

    @GET(ENDPOINT_BREEDS)
    fun getBreedsAsync(): Deferred<List<Breed>?>

    /**
     * @param limit:  On the API guideline the max limit was only up to 25
     */
    @GET(ENDPOINT_IMAGES)
    fun getDogImagesAsync(
        @Query("breed_ids")
        breedId: Int,
        @Query("limit")
        limit: Int = 25
    ): Deferred<DogImages>

}