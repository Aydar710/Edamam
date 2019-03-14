package com.m.edamam.repositories

import com.m.edamam.MyApplication
import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeRepository( val api: EdamamApi) {

    private var repositoryDb: RecipeRepositoryDb? = null

    init {
        MyApplication.context?.let {
            repositoryDb = RecipeRepositoryDb(it)
        }
    }

    fun getRecipesByName(query: String): Single<List<Hit>> =
            api.getRecipesByName(query)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.hits?: ArrayList(1)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getRecipeById(query: String): Single<Recipe?> {
        return api
                .getRecipeById(query)
                .map {
                    it.hits?.get(0)?.recipe
                }
                .map {
                    repositoryDb?.clear()
                    repositoryDb?.save(it)
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRecipesByName(query: String, currentPage: Int, pagSize : Int): Single<List<Hit>> {
        return api.getRecipesByName(query,
                from = "${pagSize * currentPage}", to = "${pagSize * (currentPage + 1)}")
                .map {
                    it.hits?:ArrayList(1)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
