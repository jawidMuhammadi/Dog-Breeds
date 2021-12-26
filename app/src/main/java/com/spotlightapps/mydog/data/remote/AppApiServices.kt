package com.spotlightapps.mydog.data.remote

import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-10-2021.
 */

interface AppApiServices {

    @GET(ENDPOINT_BREEDS)
    suspend fun getBreedsAsync(): List<BreedApiModel>?

    /**
     * @param limit:  On the API guideline the max limit was only up to 25
     */
    @GET(ENDPOINT_IMAGES)
    suspend fun getDogImagesAsync(
        @Query("breed_ids")
        breedId: Int,
        @Query("limit")
        limit: Int = 25
    ): List<DogImagesApiModel>

}