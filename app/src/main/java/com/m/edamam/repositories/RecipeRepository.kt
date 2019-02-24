package com.m.edamam.repositories

import com.m.edamam.pojo.RecipesResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RecipeRepository(val api : EdamamApi) {

    fun getRecipesByName(query : String): Single<RecipesResponse>? {
            return api.getRecipesByName(query)
                    .subscribeOn(Schedulers.io())
    }

}