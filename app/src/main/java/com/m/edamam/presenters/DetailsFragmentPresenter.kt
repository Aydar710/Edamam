package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView

@InjectViewState
class DetailsFragmentPresenter : MvpPresenter<DetailsFragmentView>() {
    var repository: RecipeRepository

    init {
        repository = RecipeRepository(Retrofit.instance.getEdamamService())
    }

    @SuppressLint("CheckResult")
    fun getRecipeDetails(id: String) {
        repository.getRecipeById(id)
                .subscribe(
                        {
                            it?.let { it1 -> viewState.showRecipeDetails(it1) }
                        },
                        {
                            it.printStackTrace()
                        }
                )
    }
}
