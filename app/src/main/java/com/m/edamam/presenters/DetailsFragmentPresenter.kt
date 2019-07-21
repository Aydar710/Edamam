package com.m.edamam.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView

@InjectViewState
open class DetailsFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<DetailsFragmentView>() {

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showRecipeDetails()
    }
}
