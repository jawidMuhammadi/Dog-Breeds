package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class BreedApiModel(
    @SerializedName("bred_for")
    var bredFor: String? = null,
    @SerializedName("breed_group")
    var breedGroup: String? = null,
    @SerializedName("height")
    var height: Height? = null,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("life_span")
    var lifeSpan: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("origin")
    var origin: String? = null,
    @SerializedName("reference_image_id")
    var referenceImageId: String? = null,
    @SerializedName("temperament")
    var temperament: String? = null,
    @SerializedName("weight")
    var weight: Weight? = null
) {
    fun toBreedModel() = Breed(
        id = id,
        name = name
    )
}

data class Breed(
    var id: Int?,
    var name: String?
)