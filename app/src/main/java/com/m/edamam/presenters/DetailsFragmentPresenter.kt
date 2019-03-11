package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView

@InjectViewState
open class DetailsFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<DetailsFragmentView>() {

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadRecipeDetails()
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
