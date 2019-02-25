package com.m.edamam.repositories

import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeRepository(val api: EdamamApi) {

    fun getRecipesByName(query: String): Single<List<Hit>> =
            api.getRecipesByName(query)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.hits
                    }


    fun getRecipeById(query: String): Single<Recipe?> {
        return api
                .getRecipeById(query)
                .map {
                    it.hits?.get(0)?.recipe
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
