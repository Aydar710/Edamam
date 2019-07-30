package com.m.edamam.data.repositories

import com.m.edamam.data.pojo.RecipesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {
    @GET("search")
    fun getRecipesByName(
            @Query("q") query: String
    ): Single<RecipesResponse>

    @GET("search")
    fun getRecipeById(
            @Query("q") query: String
    ): Single<RecipesResponse>

    @GET("search")
    fun getRecipesByName(
            @Query("q") query: String,
            @Query("from") from: String,
            @Query("to") to: String
    ): Single<RecipesResponse>
}
