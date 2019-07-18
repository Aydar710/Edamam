package com.m.edamam.di.module

import com.m.edamam.RecipeListAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    fun provideRecipeListAdapter() : RecipeListAdapter =
            RecipeListAdapter()
}
