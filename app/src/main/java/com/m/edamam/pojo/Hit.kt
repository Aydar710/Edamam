package com.m.edamam.pojo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hit(
        var id: Int? = null,
        @SerializedName("recipe")
        @Expose
        var recipe: Recipe? = null,
        @SerializedName("bookmarked")
        @Expose
        var bookmarked: Boolean? = null,
        @SerializedName("bought")
        @Expose
        var bought: Boolean? = null
)
