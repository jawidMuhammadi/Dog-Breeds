package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("breed_group")
    var breedGroup: String?,
    @SerializedName("height")
    var height: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("life_span")
    var lifeSpan: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("weight")
    var weight: String?
)