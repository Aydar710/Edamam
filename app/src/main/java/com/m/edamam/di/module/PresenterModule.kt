package com.m.edamam.di.module

import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.presenters.RecommendationFragmentPresenter
import com.m.edamam.repositories.RecipeRepository
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
    fun provideDetailsFragmentPresenter(repository: RecipeRepository): DetailsFragmentPresenter =
            DetailsFragmentPresenter(repository)

    @Provides
    fun provideRecommendationFragmentPresenter(repository: RecipeRepository):
            RecommendationFragmentPresenter =
            RecommendationFragmentPresenter(repository)
}
