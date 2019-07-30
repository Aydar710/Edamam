package com.m.edamam.di.component

import com.m.edamam.di.module.PresenterModule
import com.m.edamam.presentation.recipedetails.DetailsFragmentPresenter
import com.m.edamam.presentation.recipelist.RecipeListFragmentPresenter
import com.m.edamam.presentation.recommendation.RecommendationFragmentPresenter
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface PresenterComponent {
    fun getRecipeListFragmentPresenter(): RecipeListFragmentPresenter
    fun getDetailsFragmentPresenter(): DetailsFragmentPresenter
    fun getRecommendationFragmentPresenter(): RecommendationFragmentPresenter

    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder
}
