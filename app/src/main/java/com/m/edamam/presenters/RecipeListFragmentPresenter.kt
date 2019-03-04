package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class RecipeListFragmentPresenter : MvpPresenter<RecipeListFragmentView>() {
     var repository: RecipeRepository
     var adapter: RecipeListAdapter

    init {
        val retrofit = Retrofit.instance
        repository = RecipeRepository(retrofit.getEdamamService())
        adapter = RecipeListAdapter()
    }

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it?.let { list ->
                                viewState.submitListIntoAdapter(list)
                            }
                        },
                        {
                            it.printStackTrace()
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
}
