package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.MyApplication
import com.m.edamam.Retrofit
import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.constants.RECIPE_ID_2
import com.m.edamam.constants.RECIPE_ID_3
import com.m.edamam.constants.RECIPE_ID_4
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
        listOfRecipeIds.add(RECIPE_ID_1)
        listOfRecipeIds.add(RECIPE_ID_2)
        listOfRecipeIds.add(RECIPE_ID_3)
        listOfRecipeIds.add(RECIPE_ID_4)
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
