package com.m.edamam.views

import com.m.edamam.pojo.Recipe
import io.reactivex.Single

interface DetailsFragmentView {
    fun loadRecipeDetails()
    fun showRecipeDetails(recipe: Single<Recipe>?)
}