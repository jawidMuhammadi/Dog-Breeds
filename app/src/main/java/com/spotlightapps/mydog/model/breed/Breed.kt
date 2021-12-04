package com.spotlightapps.mydog.model.breed

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)