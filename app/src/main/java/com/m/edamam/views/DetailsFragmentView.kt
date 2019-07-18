package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.m.edamam.pojo.Recipe

@StateStrategyType(SingleStateStrategy::class)
interface DetailsFragmentView : MvpView {
    fun loadRecipeDetails()
    fun showRecipeDetails(recipe: Recipe)
    fun handleError(error: Throwable)
}
