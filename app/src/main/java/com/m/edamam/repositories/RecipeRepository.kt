package com.m.edamam.repositories

import com.m.edamam.pojo.Hit
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RecipeRepository(val api : EdamamApi) {

    fun getRecipesByName(query : String): Single<List<Hit>?> {
            return api.getRecipesByName(query)
                    .map {
                        it.hits
                    }
                    .subscribeOn(Schedulers.io())
    }
}