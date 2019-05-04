package com.m.edamam.repositories

import com.m.edamam.pojo.RecipesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {
    @GET("search")
    fun getRecipesByNameAsync(
            @Query("q") query: String
    ): Deferred<RecipesResponse>

    @GET("search")
    fun getRecipeByIdAsync(
            @Query("q") query: String
    ): Deferred<RecipesResponse>

    @GET("search")
    fun getRecipesByNameAsync(
            @Query("q") query: String,
            @Query("from") from: String,
            @Query("to") to: String
    ): Deferred<RecipesResponse>
}
