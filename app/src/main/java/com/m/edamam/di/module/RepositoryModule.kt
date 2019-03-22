package com.m.edamam.di.module

import com.m.edamam.database.RecipeDb
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.repositories.RecipeRepositoryDb
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModule::class, RoomDbModule::class])
class RepositoryModule {

    @Provides
    fun provideRecipeRepository(edamamService: EdamamApi, repositoryDb: RecipeRepositoryDb)
            : RecipeRepository =
            RecipeRepository(edamamService, repositoryDb)

    @Provides
    fun provideRecipeRepositoryDb(db: RecipeDb): RecipeRepositoryDb =
            RecipeRepositoryDb(db.getRecipeDao())
}
