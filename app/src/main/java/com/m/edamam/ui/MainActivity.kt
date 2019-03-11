package com.m.edamam.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.facebook.stetho.Stetho
import com.m.edamam.R
import com.m.edamam.RecipeListAdapter
import com.m.edamam.pojo.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import com.amitshekhar.DebugDB
import com.m.edamam.constants.SPREF_PAG_SIZE
import com.m.edamam.constants.TEST_RECIPE_ID

class MainActivity : AppCompatActivity(), RecipeListAdapter.ListItemClickListener,
        PaginationSizeFragmentDialog.PaginationSizeDialogListener,
        RecommendationFragment.BtnSearchClickListener {

    var listener: OnQueryTextListener? = null
    var sPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)
        Log.i("db", DebugDB.getAddressLog())

        //doRecipeListTransaction()
        doRecommendationFragmentTransaction()
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

    override fun onClick(recipe: Recipe) {
        val recipeId = getRecipeId(recipe)
        doRecipeDetailsFragmentTransaction(recipeId)
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
        doRecipeListTransaction()
    }

    private fun openDialog() {
        var dialog: PaginationSizeFragmentDialog = PaginationSizeFragmentDialog()
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun doRecipeListTransaction() {
        val fragmentManager = supportFragmentManager
        val fragment = RecipeListFragment()
        listener = fragment
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit()
    }

    private fun doRecipeDetailsFragmentTransaction(recipeId: String) {
        val fragmentManager = supportFragmentManager
        val fragment = DetailsFragment.newInstance(recipeId)
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit()
    }

    private fun doRecommendationFragmentTransaction() {
        val fragmentManager = supportFragmentManager
        val fragment = RecommendationFragment.newInstance()
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit()
    }

    fun getRecipeId(recipe: Recipe): String =
            TEST_RECIPE_ID

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
    }
}