package com.m.edamam.di.component

import com.m.edamam.di.module.NetModule
import com.m.edamam.di.module.PresenterModule
import com.m.edamam.di.module.ServiceModule
import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.presenters.RecommendationFragmentPresenter
import com.m.edamam.ui.RecipeListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface PresenterComponent {
    fun getRecipeListFragmentPresenter(): RecipeListFragmentPresenter
    fun getDetailsFragmentPresenter(): DetailsFragmentPresenter
    fun getRecommendationFragmentPresenter(): RecommendationFragmentPresenter
    //fun inject(fragment : RecipeListFragment)
}
