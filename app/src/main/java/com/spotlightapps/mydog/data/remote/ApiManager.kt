package com.spotlightapps.mydog.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 07-10-2021.
 */

const val CONTENT_TYPE_VALUE: String = "application/json"
const val ACCEPT_VALUE = "application/json"
const val READ_TIME_OUT: Long = 60000
const val WRITE_TIME_OUT: Long = 60000
const val CONTENT_TYPE = "Content-Type"
const val ACCEPT = "Accept"
const val ACCESS_KEY = "api_key"

class ApiManager @Inject constructor() : Interceptor {

    private lateinit var retrofit: Retrofit
    var appApiServices: AppApiServices

    init {
        createService()
        appApiServices = createApi(AppApiServices::class.java)
    }

    private fun createService() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
    }


    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(this)
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun <T> createApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter(
            ACCESS_KEY, API_KEY
        ).build()
        val newRequest = request.newBuilder()
            .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .addHeader(ACCEPT, ACCEPT_VALUE)
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}