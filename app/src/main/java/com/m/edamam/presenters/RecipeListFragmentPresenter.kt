package com.m.edamam.presenters

import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers

class RecipeListFragmentPresenter(val view: RecipeListFragmentView) {
    var repository: RecipeRepository
    var adapter: RecipeListAdapter

    init {
        repository = RecipeRepository(Retrofit.instance.getEdamamService())
        adapter = RecipeListAdapter()
    }

    fun updateAdapter(query: String) {
        repository
                .getRecipesByName(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it?.let { list ->
                                view.submitListIntoAdapter(list)
                            }
                        },
                        {
                            it.printStackTrace()
                        }
                )

    }
}
