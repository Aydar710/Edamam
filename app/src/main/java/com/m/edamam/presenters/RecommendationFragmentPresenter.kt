package com.m.edamam.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecommendationFragmentView

@InjectViewState
class RecommendationFragmentPresenter : MvpPresenter<RecommendationFragmentView>() {
    lateinit var repository: RecipeRepository

    init {
        repository = RecipeRepository(Retrofit.instance.getEdamamService())
    }

}