package com.m.edamam.data.repositories

import com.m.edamam.data.pojo.Hit
import com.m.edamam.data.pojo.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RecipeRepository(val api: EdamamApi, private var repositoryDb: RecipeRepositoryDb) {

    fun getRecipesByName(query: String): Single<List<Hit>> =
        api.getRecipesByName(query)
            .subscribeOn(Schedulers.io())
            .map {
                it.hits ?: ArrayList(1)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    open fun getRecipeById(query: String): Single<Recipe?> {
        return api
            .getRecipeById(query)
            .map {
                it.hits?.get(0)?.recipe
            }
            .map {
                repositoryDb.clear()
                repositoryDb.save(it)
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRecipesByName(query: String, currentPage: Int, pagSize: Int): Single<List<Hit>> {
        return api.getRecipesByName(query,
            from = "${pagSize * currentPage}", to = "${pagSize * (currentPage + 1)}")
            .map {
                it.hits ?: ArrayList(1)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
