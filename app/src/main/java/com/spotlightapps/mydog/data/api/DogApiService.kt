package com.spotlightapps.mydog.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-10-2021.
 */

interface DogApiService {

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

    companion object {

        private const val ACCESS_KEY = "api_key"
        private const val API_KEY: String = "e5a34e77-afd0-4a80-8740-e38d7e2a1a6f"
        private const val BASE_URL: String = "https://api.thedogapi.com/v1/"
        private const val ENDPOINT_BREEDS = "breeds"
        private const val ENDPOINT_IMAGES = "images/search"

        fun create(): DogApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val interceptor = Interceptor { chain ->
                val request: Request = chain.request()
                val newUrl = request.url.newBuilder().addQueryParameter(
                    ACCESS_KEY, API_KEY
                ).build()
                val newRequest = request.newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(DogApiService::class.java)
        }
    }
}