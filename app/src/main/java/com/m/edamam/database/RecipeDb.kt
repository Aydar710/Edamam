package com.m.edamam.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.m.edamam.pojo.Recipe

@TypeConverters(IngredientsConverter::class)
@Database(entities = arrayOf(Recipe::class), version = 1, exportSchema = false)
abstract class RecipeDb : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}
