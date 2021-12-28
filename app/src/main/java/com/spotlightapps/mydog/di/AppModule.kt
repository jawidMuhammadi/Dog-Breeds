package com.spotlightapps.mydog.di

import com.spotlightapps.mydog.DefaultDogRepository
import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.data.api.DogApiService
import com.spotlightapps.mydog.data.api.DogListRemoteDataSource
import com.spotlightapps.mydog.data.api.DogListRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesDogRemoteDataSource(dogApiService: DogApiService): DogListRemoteDataSource =
        DogListRemoteDataSourceImp(dogApiService)

    @Singleton
    @Provides
    fun providesDogApiServices(): DogApiService = DogApiService.create()

    @Singleton
    @Provides
    fun provideDogRepository(dogApiService: DogListRemoteDataSource): DogRepository =
        DefaultDogRepository(dogApiService)

}