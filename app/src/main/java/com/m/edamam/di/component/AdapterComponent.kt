package com.m.edamam.di.component

import com.m.edamam.RecipeListAdapter
import com.m.edamam.di.module.AdapterModule
import dagger.Component

@Component(modules = [AdapterModule::class])
interface AdapterComponent {
    fun getRecipeListAdapter() : RecipeListAdapter
}
