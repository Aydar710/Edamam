package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView

@InjectViewState
open class RecipeListFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<RecipeListFragmentView>() {

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
                .subscribe(
                        {
                            it?.let { list ->
//                                val mList: MutableList<Hit> = ArrayList()
//                                mList.addAll(list)
                                viewState.submitListIntoAdapter(list)
                            }
                        },
                        {
                            viewState.showError(it)
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
                            viewState.showError(it)
                        })
    }
}
