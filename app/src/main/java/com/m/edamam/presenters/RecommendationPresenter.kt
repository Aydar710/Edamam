package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.constants.RECIPE_ID_2
import com.m.edamam.constants.RECIPE_ID_3
import com.m.edamam.constants.RECIPE_ID_4
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecommendationFragmentView
import java.util.*

@InjectViewState
class RecommendationPresenter(
        private var repository: RecipeRepository
) : MvpPresenter<RecommendationFragmentView>() {

    var listOfRecipeIds: ArrayList<String> = ArrayList()

    init {

        listOfRecipeIds.add(RECIPE_ID_1)
        listOfRecipeIds.add(RECIPE_ID_2)
        listOfRecipeIds.add(RECIPE_ID_3)
        listOfRecipeIds.add(RECIPE_ID_4)
    }

    @SuppressLint("CheckResult")
    fun getRecommendedRecipe() {
        repository
                .getRecipeById(getRandomRecipeIdFromList())
                .subscribe(
                        { it?.let(viewState::showRecommendedRecipe) },
                        viewState::showError
                )
    }

    /*fun getRecommendedRecipeFromDB() {
        repositoryDb?.getRecommendedRecipe()
    }*/

    fun getRandomRecipeIdFromList(): String {
        val random = Random()
        val randomInt = random.nextInt(listOfRecipeIds.size)
        return listOfRecipeIds[randomInt]
    }
}
