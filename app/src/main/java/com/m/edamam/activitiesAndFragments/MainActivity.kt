package com.m.edamam.activitiesAndFragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.facebook.stetho.Stetho
import com.m.edamam.R
import com.m.edamam.RecipeListAdapter
import com.m.edamam.Retrofit
import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class MainActivity : AppCompatActivity(), RecipeListAdapter.ListItemClickListener {

    lateinit var listener: OnQueryTextListener

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
                listener.onQueryTextChanged(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) return true
                listener.onQueryTextChanged(query)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(recipe: Recipe) {
        val recipeId = getRecipeId(recipe)
        doRecipeDetailsFragment(recipeId)
    }

    fun doRecipeListTransaction(){
        val fragmentManager = supportFragmentManager
        val fragment = RecipeListFragment()
        listener = fragment
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
    }

    fun doRecipeDetailsFragment(recipeId : String){
        val fragmentManager = supportFragmentManager
        val fragment = DetailsFragment.newInstance(recipeId)
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
    }

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
    }

    fun getRecipeId(recipe : Recipe): String {
        return "1a39cf9cd8181d38ac551e5a4879ea66"
    }

}
