package com.m.edamam.repositories

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RecipeRepository(val api : EdamamApi) {

    fun getRecipesByName(query : String): Single<String?> {
            return api.getRecipesByName(query)
                    .map {
                        it.q
                    }
                    .subscribeOn(Schedulers.io())
    }

}