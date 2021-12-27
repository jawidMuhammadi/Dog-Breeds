package com.spotlightapps.mydog

import com.spotlightapps.mydog.model.dogimage.Breed
import com.spotlightapps.mydog.model.dogimage.DogImage

/**
 * Created by Ahmad Jawid Muhammadi
 * on 26-12-2021.
 */

object TestData {

    val dogImage1 = DogImage("1", "url1")
    val dogImage2 = DogImage("2", "url2")
    val dogImageList = listOf(dogImage1, dogImage2)

    val dogBreed1 = Breed(1, "breed1")
    val dogBreed2 = Breed(2, "breed2")
    val breedList = listOf(dogBreed1, dogBreed2)
}