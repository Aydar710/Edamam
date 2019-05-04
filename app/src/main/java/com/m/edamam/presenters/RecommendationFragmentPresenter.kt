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
import kotlinx.coroutines.*
import java.util.*

@InjectViewState
open class RecommendationFragmentPresenter(
        private var repository: RecipeRepository
) : MvpPresenter<RecommendationFragmentView>() {

    var getRecommendedRecipeJob: Job? = null

    var listOfRecipeIds: ArrayList<String> = ArrayList()

    init {

        listOfRecipeIds.add(RECIPE_ID_1)
        listOfRecipeIds.add(RECIPE_ID_2)
        listOfRecipeIds.add(RECIPE_ID_3)
        listOfRecipeIds.add(RECIPE_ID_4)
    }

    @SuppressLint("CheckResult")
    fun getRecommendedRecipe() {
        getRecommendedRecipeJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipe = repository.getRecipeById(getRandomRecipeIdFromList())
                withContext(Dispatchers.Main) {
                    recipe?.let { viewState.showRecommendedRecipe(it) }
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    /*fun getRecommendedRecipeFromDB() {
        repositoryDb?.getRecommendedRecipe()
    }*/

    open fun getRandomRecipeIdFromList(): String {
        val random: Random = Random()
        val randomInt = random.nextInt(listOfRecipeIds.size)
        return listOfRecipeIds[randomInt]
    }

    override fun onDestroy() {
        getRecommendedRecipeJob?.cancel()
        super.onDestroy()
    }
}
