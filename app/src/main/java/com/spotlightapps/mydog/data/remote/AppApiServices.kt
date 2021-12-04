package com.spotlightapps.mydog.data.remote

import com.spotlightapps.mydog.model.breed.Breed
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-10-2021.
 */

interface AppApiServices {

    @GET(ENDPOINT_BREEDS)
    fun getBreedsAsync(): Deferred<List<Breed>?>
//
//    @GET(ENDPOINT_SYMBOLS)
//    fun getCountrySymbolsAsync(): Deferred<SymbolsResponse?>

}