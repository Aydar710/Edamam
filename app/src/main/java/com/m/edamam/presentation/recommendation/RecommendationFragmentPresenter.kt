package com.m.edamam.presentation.recommendation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.constants.RECIPE_ID_2
import com.m.edamam.constants.RECIPE_ID_3
import com.m.edamam.constants.RECIPE_ID_4
import com.m.edamam.data.repositories.RecipeRepository
import com.m.edamam.presentation.common.Screens
import ru.terrakok.cicerone.Router
import java.util.*

@InjectViewState
open class RecommendationFragmentPresenter(
    private var repository: RecipeRepository, private var router: Router
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
        repository.getRecipeById(getRandomRecipeIdFromList())
            .doOnSubscribe {
                viewState.showProgress()
            }
            .doFinally {
                viewState.hideProgress()
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

    open fun getRandomRecipeIdFromList(): String {
        val random: Random = Random()
        val randomInt = random.nextInt(listOfRecipeIds.size)
        return listOfRecipeIds[randomInt]
    }

    fun moveToRecipeListScreen() {
        router.replaceScreen(Screens.RecipeListScreen())
    }
}
