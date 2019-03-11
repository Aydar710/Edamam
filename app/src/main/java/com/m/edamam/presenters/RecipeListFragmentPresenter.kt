package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.RecipeListAdapter
import com.m.edamam.pojo.Hit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
open class RecipeListFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<RecipeListFragmentView>() {

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it?.let { list ->
                                val mList: MutableList<Hit> = ArrayList()
                                mList.addAll(list)
                                viewState.submitListIntoAdapter(mList)
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
}
