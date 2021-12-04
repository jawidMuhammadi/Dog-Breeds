package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class DogImagesItem(
    @SerializedName("breeds")
    var breeds: List<Breed>?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)