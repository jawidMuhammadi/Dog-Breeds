package com.spotlightapps.mydog.di

import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.FakeDogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
@Module
class TestAppModule {

    @Singleton
    @Provides
    fun provideFakeDogRepository(): DogRepository = FakeDogRepository()
}