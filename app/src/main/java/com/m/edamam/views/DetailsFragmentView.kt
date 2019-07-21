package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface DetailsFragmentView : MvpView {
    fun showRecipeDetails()
    fun handleError(error: Throwable)
}
