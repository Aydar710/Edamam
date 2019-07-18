package com.m.edamam.database

import android.arch.persistence.room.TypeConverter
import java.lang.StringBuilder
import java.util.*

class IngredientsConverter {

    @TypeConverter
    fun FromIngredients(ingredientsString: String): List<String> =
         Arrays.asList(ingredientsString.split(","))[0]

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
}
