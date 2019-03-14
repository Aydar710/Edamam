package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView

@InjectViewState
class RecipeListFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<RecipeListFragmentView>() {

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
                .subscribe(
                        { it?.let(viewState::submitListIntoAdapter) },
                        viewState::showError
                )
    }

    @SuppressLint("CheckResult")
    fun loadNextElements(currentPage: Int, query: String, pagSise: Int) {
        repository
                .getRecipesByName(query = query, currentPage = currentPage, pagSize = pagSise)
                .subscribe(
                        { it?.let(viewState::addElementsToAdapter) },
                        viewState::showError
                )
    }
}
