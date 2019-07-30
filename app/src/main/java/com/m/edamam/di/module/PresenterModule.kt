package com.m.edamam.di.module

import com.m.edamam.data.repositories.RecipeRepository
import com.m.edamam.presentation.recipedetails.DetailsFragmentPresenter
import com.m.edamam.presentation.recipelist.RecipeListFragmentPresenter
import com.m.edamam.presentation.recommendation.RecommendationFragmentPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module(includes = [RepositoryModule::class, NavigationModule::class])
class PresenterModule {

    @Provides
    fun provideRecipeListPresenter(repository: RecipeRepository, router: Router)
            : RecipeListFragmentPresenter =
        RecipeListFragmentPresenter(repository, router)

    @Provides
    fun provideDetailsFragmentPresenter(repository: RecipeRepository, router: Router): DetailsFragmentPresenter =
        DetailsFragmentPresenter(repository, router)

    @Provides
    fun provideRecommendationFragmentPresenter(repository: RecipeRepository, router : Router):
        RecommendationFragmentPresenter =
        RecommendationFragmentPresenter(repository, router)
}
