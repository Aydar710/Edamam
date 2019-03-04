package com.m.edamam.pojo

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.m.edamam.database.IngredientsConverter

@Entity(tableName = "recipes")
data class Recipe(
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0,
        @SerializedName("uri")
        @Expose
        var uri: String? = null,
        @SerializedName("label")
        @Expose
        var label: String? = null,
        @SerializedName("image")
        @Expose
        var image: String? = null,
        @SerializedName("source")
        @Expose
        @Ignore
        var source: String? = null,
        @SerializedName("url")
        @Expose
        var url: String? = null,
        @SerializedName("shareAs")
        @Expose
        @Ignore
        var shareAs: String? = null,
        @SerializedName("yield")
        @Expose
        @Ignore
        var yield: Double? = null,
        @SerializedName("dietLabels")
        @Expose
        @Ignore
        var dietLabels: List<String>? = null,
        @SerializedName("healthLabels")
        @Expose
        @Ignore
        var healthLabels: List<String>? = null,
        @SerializedName("cautions")
        @Expose
        @Ignore
        var cautions: List<Any>? = null,
        @SerializedName("ingredientLines")
        @Expose
        @TypeConverters(IngredientsConverter::class)
        var ingredientLines: List<String>? = null,
        @SerializedName("ingredients")
        @Expose
        @Ignore
        var ingredients: List<Ingredient>? = null,
        @SerializedName("calories")
        @Expose
        var calories: Double? = null,
        @SerializedName("totalWeight")
        @Expose
        var totalWeight: Double? = null,
        @SerializedName("totalTime")
        @Expose
        var totalTime: Double? = null
)
