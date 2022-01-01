package com.spotlightapps.mydog.domain

import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.di.IoDispatcher
import com.spotlightapps.mydog.model.dogimage.Breed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 31-12-2021.
 */

class LoadBreedListUseCase @Inject constructor(
    private val repository: DogRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : UseCase<Boolean, List<Breed>?>(coroutineDispatcher) {

    override suspend fun execute(parameters: Boolean): List<Breed>? {
        return repository.getBreedList(parameters)
    }
}