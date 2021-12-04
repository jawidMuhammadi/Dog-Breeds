package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("bred_for")
    var bredFor: String?,
    @SerializedName("breed_group")
    var breedGroup: String?,
    @SerializedName("height")
    var height: Height?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("life_span")
    var lifeSpan: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("origin")
    var origin: String?,
    @SerializedName("reference_image_id")
    var referenceImageId: String?,
    @SerializedName("temperament")
    var temperament: String?,
    @SerializedName("weight")
    var weight: Weight?
)