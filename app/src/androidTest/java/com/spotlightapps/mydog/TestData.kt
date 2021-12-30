package com.spotlightapps.mydog

import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.BreedApiModel
import com.spotlightapps.mydog.model.dogimage.DogImage
import com.spotlightapps.mydog.model.dogimage.DogImagesApiModel

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */

object AndroidTestData {

    val dogImage1 = DogImage("1", "url1")
    val dogImage2 = DogImage("2", "url2")
    val dogImageList = listOf(dogImage1, dogImage2)

    val dogBreed1 = Breed(1, "breed1")
    val dogBreed2 = Breed(2, "breed2")
    val breedList = listOf(dogBreed1, dogBreed2)

    val dogImageApiModel1 = DogImagesApiModel(id = "1", url = "url1")
    val dogImageApiModel2 = DogImagesApiModel(id = "2", url = "url2")
    val dogImageApiModelList = listOf(dogImageApiModel1, dogImageApiModel2)

    val breedApiModel1 = BreedApiModel(id = 1, name = "breed1")
    val breedApiModel2 = BreedApiModel(id = 2, name = "breed2")
    val breedApiModelList = listOf(breedApiModel1, breedApiModel2)

}