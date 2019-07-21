package com.m.edamam.ui

import android.support.v4.app.Fragment
import com.m.edamam.pojo.Recipe
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class RecommendationScreen : SupportAppScreen() {

        override fun getFragment(): Fragment =
                RecommendationFragment.newInstance()
    }

    class DetailsScreen(val recipe: Recipe) : SupportAppScreen() {

        override fun getFragment(): Fragment =
                DetailsFragment.newInstance(recipe)
    }

    class RecipeListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val fragment : RecipeListFragment by lazy {
                RecipeListFragment()
            }
            return fragment
        }
    }
}
