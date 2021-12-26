package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class DogImagesApiModel(
    @SerializedName("breeds")
    var breedApiModels: List<BreedApiModel>?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
) {
    fun toDogImageModel() = DogImage(
        url = url,
        id = id
    )
}

data class DogImage(
    var id: String?,
    var url: String?
)

 data class Height(
    @SerializedName("imperial")
    var imperial: String?,
    @SerializedName("metric")
    var metric: String?
)

 data class Weight(
    @SerializedName("imperial")
    var imperial: String?,
    @SerializedName("metric")
    var metric: String?
)