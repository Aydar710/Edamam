package com.m.edamam.presentation.recommendation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.m.edamam.data.pojo.Recipe

@StateStrategyType(SingleStateStrategy::class)
interface RecommendationFragmentView : MvpView {
    fun getRecommendedRecipe()
    fun showRecommendedRecipe(recipe : Recipe)
    fun showProgress()
    fun hideProgress()
}
