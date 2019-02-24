package com.m.edamam.repositories

import com.m.edamam.pojo.Recipe
import com.m.edamam.pojo.RecipesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {
    @GET("search")
    fun getRecipesByName(
            @Query("q") query: String)
            : Single<RecipesResponse>

    @GET("search")
    fun getRecipeById(@Query("q") query: String?) : Single<Recipe>
}
