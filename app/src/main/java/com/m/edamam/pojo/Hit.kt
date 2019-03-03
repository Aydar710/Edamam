package com.m.edamam.pojo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes")
data class Hit(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @SerializedName("recipe")
        @Expose
        @Embedded
        var recipe: Recipe? = null,
        @SerializedName("bookmarked")
        @Expose
        @Ignore
        var bookmarked: Boolean? = null,
        @SerializedName("bought")
        @Expose
        @Ignore
        var bought: Boolean? = null
)
