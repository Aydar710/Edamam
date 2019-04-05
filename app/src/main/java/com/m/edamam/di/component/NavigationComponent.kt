package com.m.edamam.di.component

import com.m.edamam.di.module.NavigationModule
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent {

    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder
}
