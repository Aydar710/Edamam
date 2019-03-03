package com.m.edamam.database

import android.arch.persistence.room.TypeConverter
import java.lang.StringBuilder
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors

class IngredientsConverter {

    @TypeConverter
    fun toIngredients(ingredients: List<String>): String? {
        /*return ingredients.stream()
                .collect(Collectors.joining(","))*/
        var ingredientsString: StringBuilder = StringBuilder()
        ingredients.forEach {
            ingredientsString.append("${it},")
        }

        return ingredientsString.toString()
    }

    @TypeConverter
    fun FromIngredients(ingredientsString: String): List<String> {
        return Arrays.asList(ingredientsString.split(","))[0]
    }
}