package com.m.edamam.di.module

import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Provides
    fun provideRecipeRepository(edamamService: EdamamApi): RecipeRepository =
            RecipeRepository(edamamService)
}
