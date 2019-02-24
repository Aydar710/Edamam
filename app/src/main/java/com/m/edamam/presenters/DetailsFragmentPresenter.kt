package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView

class DetailsFragmentPresenter(val view: DetailsFragmentView) {
    @SuppressLint("CheckResult")
    fun getRecipeDetails(id: String?) {
        view.showRecipeDetails(repository.getRecipeById(id))
    }

    var repository: RecipeRepository
    init {
        val retrofit = Retrofit.instance
        repository = RecipeRepository(retrofit.getEdamamService())
    }
}