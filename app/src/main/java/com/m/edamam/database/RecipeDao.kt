package com.m.edamam.database

import android.arch.persistence.room.*
import com.m.edamam.pojo.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getAllAsList(): List<Recipe>

    @Query("DELETE FROM recipes")
    fun clear()
}
