package com.m.edamam.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ingredient (

    @SerializedName("text")
    @Expose
    var text: String? = null,
    @SerializedName("weight")
    @Expose
    var weight: Double? = null
)
