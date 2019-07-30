package com.m.edamam.presentation.common

import android.support.v4.app.Fragment
import com.m.edamam.data.pojo.Recipe
import com.m.edamam.presentation.recipedetails.DetailsFragment
import com.m.edamam.presentation.recipelist.RecipeListFragment
import com.m.edamam.presentation.recommendation.RecommendationFragment
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
