package com.spotlightapps.mydog.domain

import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.di.IoDispatcher
import com.spotlightapps.mydog.model.dogimage.DogImage
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 31-12-2021.
 */

data class DogImageRequest(var isToRefresh: Boolean, var breedId: Int)

class LoadDogImagesUseCase @Inject constructor(
    private val dogRepository: DogRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase<DogImageRequest, List<DogImage?>>(coroutineDispatcher) {

    override suspend fun execute(parameters: DogImageRequest): List<DogImage?> {
        return dogRepository.getDogImageList(parameters.breedId, parameters.isToRefresh)
    }

}