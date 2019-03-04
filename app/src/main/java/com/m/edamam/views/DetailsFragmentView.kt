package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.m.edamam.pojo.Recipe

interface DetailsFragmentView : MvpView {
    fun loadRecipeDetails()
    fun showRecipeDetails(recipe: Recipe)
}
