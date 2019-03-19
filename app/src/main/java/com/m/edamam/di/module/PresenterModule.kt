package com.m.edamam.di.module

import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.presenters.RecommendationFragmentPresenter
import com.m.edamam.repositories.RecipeRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class PresenterModule {

    @Provides
    fun provideRecipeListPresenter(repository: RecipeRepository): RecipeListFragmentPresenter =
            RecipeListFragmentPresenter(repository)

    @Provides
    fun provideDetailsFragmentPresenter(repository: RecipeRepository) : DetailsFragmentPresenter =
            DetailsFragmentPresenter(repository)

    @Provides
    fun provideRecommendationFragmentPresenter(repository: RecipeRepository) :
            RecommendationFragmentPresenter =
            RecommendationFragmentPresenter(repository)
}
