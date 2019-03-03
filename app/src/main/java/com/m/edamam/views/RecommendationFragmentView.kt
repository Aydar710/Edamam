package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.m.edamam.pojo.Recipe

interface RecommendationFragmentView : MvpView {
    fun getRecommendedRecipe()
    fun showRecommendedRecipe(recipe : Recipe)
}