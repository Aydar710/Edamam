package com.m.edamam.activitiesAndFragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import com.facebook.stetho.Stetho
import com.m.edamam.R
import com.m.edamam.RecipeListAdapter
import com.m.edamam.constants.DEBOUNCE_TIMEOUT
import com.m.edamam.constants.TEMPORARY_RECIPE_ID
import com.m.edamam.pojo.Recipe
import com.m.edamam.utils.observableFromSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), RecipeListAdapter.ListItemClickListener {

    var listener: OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)

        doRecipeListTransaction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_recipe_list_menu, menu)

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = "Search recipe"

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

        observableFromSearchView(searchView)
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                   // presenter.onSearch(it)
                }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(recipe: Recipe) {
        val recipeId = TEMPORARY_RECIPE_ID
        doRecipeDetailsFragment(recipeId)
    }

    fun doRecipeListTransaction() {
        val fragmentManager = supportFragmentManager
        val fragment = RecipeListFragment()
        listener = fragment
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit()
    }

    fun doRecipeDetailsFragment(recipeId: String) {
        val fragmentManager = supportFragmentManager
        val fragment = DetailsFragment.newInstance(recipeId)
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit()
    }

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
        fun takDelatNelzya()
    }
}
