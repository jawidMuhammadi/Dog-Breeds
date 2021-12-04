package com.spotlightapps.mydog.model.dogimage


import com.google.gson.annotations.SerializedName

data class DogImage(
    @SerializedName("breeds")
    var breeds: List<Breed>?,
    @SerializedName("categories")
    var categories: List<Any>?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("mime_type")
    var mimeType: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)