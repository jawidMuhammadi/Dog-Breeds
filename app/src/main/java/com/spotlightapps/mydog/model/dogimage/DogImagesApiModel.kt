package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class DogImagesApiModel(
    @SerializedName("breeds")
    var breedApiModels: List<BreedApiModel>? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("width")
    var width: Int? = 0
) {
    fun toDogImageModel() = DogImage(
        url = url,
        id = id
    )
}

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

data class DogImage(
    var id: String?,
    var url: String?
)