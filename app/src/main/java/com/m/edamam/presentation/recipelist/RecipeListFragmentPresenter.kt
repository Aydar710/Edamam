package com.m.edamam.presentation.recipelist

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.constants.TEST_RECIPE_ID
import com.m.edamam.data.pojo.Recipe
import com.m.edamam.data.repositories.RecipeRepository
import com.m.edamam.presentation.common.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
open class RecipeListFragmentPresenter(
        private val repository: RecipeRepository, private val router: Router
) : MvpPresenter<RecipeListFragmentView>() {

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
            .doOnSubscribe {
                viewState.showProgress()
            }
            .doFinally {
                viewState.hideProgress()
            }
                .subscribe(
                        {
                            it?.let { list ->
                                viewState.submitListIntoAdapter(list)
                            }
                        },
                        {
                            viewState.showError("Произошла ошибка при загрузке данных")
                        }
                )
    }

    @SuppressLint("CheckResult")
    fun loadNextElements(currentPage: Int, query: String, pagSise: Int) {
        repository.getRecipesByName(query = query, currentPage = currentPage, pagSize = pagSise)
                .subscribe({
                    it?.let { it1 ->
                        viewState.addElementsToAdapter(it1)
                    }
                },
                        {
                            it.printStackTrace()
                        })
    }

    fun moveToRecipeDetailsScreen(recipe: Recipe) {
        router.navigateTo(Screens.DetailsScreen(recipe))
    }

    fun getRecipeId(recipe: Recipe): String =
            TEST_RECIPE_ID
}
