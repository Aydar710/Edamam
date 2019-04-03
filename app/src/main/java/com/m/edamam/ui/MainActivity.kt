package com.m.edamam.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.facebook.stetho.Stetho
import com.m.edamam.R
import com.m.edamam.pojo.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import com.amitshekhar.DebugDB
import com.m.edamam.App
import com.m.edamam.constants.SPREF_PAG_SIZE
import com.m.edamam.constants.TEST_RECIPE_ID
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace


class MainActivity : AppCompatActivity(),
        PaginationSizeFragmentDialog.PaginationSizeDialogListener,
        RecommendationFragment.BtnSearchClickListener {

    companion object {

        var listener: OnQueryTextListener? = null
    }
    var sPref: SharedPreferences? = null
    private var navigatorHolder: NavigatorHolder? = null
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)
        Log.i("db", DebugDB.getAddressLog())

        navigatorHolder = App.presenterComponent.getNavigatorHolder()

        navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.RecommendationScreen())))

        }


        //doRecommendationFragmentTransaction()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder?.setNavigator(navigator)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_recipe_list_menu, menu)

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = "Search recipe"

        //TODO перепистаь под RX
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) return true
                listener?.onQueryTextChanged(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) return true
                listener?.onQueryTextChanged(query)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_pagination_size -> openDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        navigatorHolder?.removeNavigator()
        super.onPause()
    }

    override fun setPaginationSize(size: Int) {
        /*sPref = getPreferences(Context.MODE_PRIVATE)
        val ed = sPref?.edit()
        ed?.putInt(SPREF_PAG_SIZE, size)
        ed?.apply()*/
        getPreferences(Context.MODE_PRIVATE)?.apply {
            edit()
                    .putInt(SPREF_PAG_SIZE, size)
                    .apply()
        }
    }

    override fun onBtnSearchClicked() {
        navigator.applyCommands(arrayOf<Command>(Replace(Screens.RecipeListScreen())))
    }

    private fun openDialog() {
        var dialog: PaginationSizeFragmentDialog = PaginationSizeFragmentDialog()
        dialog.show(supportFragmentManager, "dialog")
    }

    fun getRecipeId(recipe: Recipe): String =
            TEST_RECIPE_ID

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
    }
}
