package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.pojo.Hit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class RecipeListFragmentPresenter : MvpPresenter<RecipeListFragmentView>() {
    lateinit var repository: RecipeRepository
    lateinit var adapter: RecipeListAdapter

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

    fun loadNextElements(currentPage : Int, query : String){
        repository.getRecipesByName(query =  query,
                from = "10 * currentPage"
                to =  "10 * currentPage")
    }
}