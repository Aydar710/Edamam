package com.m.edamam.presentation.common

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.amitshekhar.DebugDB
import com.facebook.stetho.Stetho
import com.m.edamam.App
import com.m.edamam.R
import com.m.edamam.constants.TEST_RECIPE_ID
import com.m.edamam.data.pojo.Recipe
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

class MainActivity : AppCompatActivity(){

    var sPref: SharedPreferences? = null
    private var navigatorHolder: NavigatorHolder? = null
    private var navigator: Navigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(this)
        Log.i("db", DebugDB.getAddressLog())

        navigatorHolder = App.presenterComponent.getNavigatorHolder()

        navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container_main)

        if (savedInstanceState == null) {
            navigator?.applyCommands(arrayOf<Command>(Replace(Screens.RecommendationScreen())))
        }
    }

    override fun onResume() {
        navigatorHolder?.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder?.removeNavigator()
        super.onPause()
    }

    fun getRecipeId(recipe: Recipe): String =
        TEST_RECIPE_ID
}
