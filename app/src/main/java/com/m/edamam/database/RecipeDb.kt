package com.m.edamam.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.m.edamam.pojo.Recipe

@TypeConverters(IngredientsConverter::class)
@Database(entities = arrayOf(Recipe::class), version = 1, exportSchema = false)
abstract class RecipeDb : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao

    companion object {
        private val DATABASE_NAME = "mDatabse.db"
        private var dbInstance: RecipeDb? = null
        fun getInstance(context : Context): RecipeDb {
            if(dbInstance == null){
                dbInstance = Room.databaseBuilder(context,
                        RecipeDb::class.java, DATABASE_NAME).build()
            }
            return dbInstance as RecipeDb
        }
    }
}
