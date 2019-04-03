package com.m.edamam.ui

import android.support.v4.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class RecommendationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment =
                RecommendationFragment.newInstance()
    }

    class DetailsScreen(val recipeId: String) : SupportAppScreen() {
        override fun getFragment(): Fragment =
                DetailsFragment.newInstance(recipeId)


    }

}
