package com.m.edamam.repositories

import android.content.Context
import com.m.edamam.MyApplication
import com.m.edamam.database.RecipeDao
import com.m.edamam.database.RecipeDb
import com.m.edamam.pojo.Recipe

class RecipeRepositoryDb(context: Context) {
    private lateinit var db: RecipeDao

    init {
        RecipeDb.getInstance(MyApplication.context).getRecipeDao()
    }

    fun getRecommendedRecipe(): Recipe? {
        return db.getAllAsList()[0]
    }

    fun save(recipe : Recipe){
        db.insert(recipe)
    }

    fun clear(){
        db.clear()
    }
}