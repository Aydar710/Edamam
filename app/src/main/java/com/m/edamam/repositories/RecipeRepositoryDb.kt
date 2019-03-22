package com.m.edamam.repositories

import android.content.Context
import com.m.edamam.App
import com.m.edamam.database.RecipeDao
import com.m.edamam.database.RecipeDb
import com.m.edamam.pojo.Recipe

open class RecipeRepositoryDb(private val db: RecipeDao) {

    fun getRecommendedRecipe(): Recipe? =
            db.getAllAsList().get(0)

    fun save(recipe: Recipe) {
        db.insert(recipe)
    }

    fun clear() {
        db.clear()
    }
}
