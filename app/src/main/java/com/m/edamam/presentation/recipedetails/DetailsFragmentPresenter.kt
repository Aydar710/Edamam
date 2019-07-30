package com.m.edamam.presentation.recipedetails

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.data.repositories.RecipeRepository
import ru.terrakok.cicerone.Router

@InjectViewState
open class DetailsFragmentPresenter(private val repository: RecipeRepository, private val router : Router)
    : MvpPresenter<DetailsFragmentView>() {

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showRecipeDetails()
    }

    fun onBackPressed(){
        router.exit()
    }
}
