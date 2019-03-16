package com.m.edamam.di.component

import com.m.edamam.di.module.NetModule
import com.m.edamam.di.module.PresenterModule
import com.m.edamam.di.module.ServiceModule
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.ui.RecipeListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ServiceModule::class,
    NetModule::class,
    PresenterModule::class
])
interface PresenterComponent {
    fun getRecipeListFragmentPresenter(): RecipeListFragmentPresenter
    //fun inject(fragment : RecipeListFragment)
}
