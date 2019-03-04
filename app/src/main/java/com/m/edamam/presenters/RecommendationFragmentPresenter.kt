package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.MyApplication
import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.repositories.RecipeRepositoryDb
import com.m.edamam.views.RecommendationFragmentView
import java.util.*

@InjectViewState
class RecommendationFragmentPresenter : MvpPresenter<RecommendationFragmentView>() {
    private  var repository: RecipeRepository
    private  var repositoryDb: RecipeRepositoryDb? = null
    private  var listOfRecipeIds: ArrayList<String>

    init {
        repository = RecipeRepository(Retrofit.instance.getEdamamService())
        MyApplication.context?.let {
            repositoryDb = RecipeRepositoryDb(it)
        }
        listOfRecipeIds = ArrayList()
        listOfRecipeIds.add("1a39cf9cd8181d38ac551e5a4879ea66")
        listOfRecipeIds.add("bbafa625a135b8c7c1b63e15d839f0e4")
        listOfRecipeIds.add("d9dd328cb9c587265743511df941fa4f")
        listOfRecipeIds.add("1a8963d37a6edc728a2e2e0754b505cf")
    }


    @SuppressLint("CheckResult")
    fun getRecommendedRecipe() {
        val random: Random = Random()
        val randomInt = random.nextInt(listOfRecipeIds.size)
        repository.getRecipeById(listOfRecipeIds[randomInt])
                .map {
                    repositoryDb?.clear()
                    repositoryDb?.save(it)
                    it
                }
                .subscribe({
                    it?.let {
                        viewState.showRecommendedRecipe(it)
                    }
                },
                        {
                            it.printStackTrace()
                        })
    }

    fun getRecommendedRecipeFromDB() {
        repositoryDb?.getRecommendedRecipe()
    }
}