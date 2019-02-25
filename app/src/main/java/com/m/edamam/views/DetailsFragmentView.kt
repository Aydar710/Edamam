package com.m.edamam.views

import com.m.edamam.pojo.Recipe

interface DetailsFragmentView {
    fun loadRecipeDetails()
    fun showRecipeDetails(recipe: Recipe)
}
