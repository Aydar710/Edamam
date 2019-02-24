package com.m.edamam.activitiesAndFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.m.edamam.R
import com.m.edamam.pojo.Hit
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.views.RecipeListFragmentView
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*

class RecipeListFragment : Fragment(), RecipeListFragmentView, MainActivity.OnQueryTextListener {
    lateinit var presenter: RecipeListFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        presenter = RecipeListFragmentPresenter(this)
        var rv = view.recycler_recipes
        rv.adapter = presenter.adapter
        return view
    }

    override fun updateAdapterByQueryResult(query: String) {
        presenter.updateAdapter(query)
    }

    override fun submitListIntoAdapter(list: List<Hit>) {
        presenter.adapter.submitList(list)
    }

    override fun onQueryTextChanged(query: String) {
        Log.i("Tag", query)
        updateAdapterByQueryResult(query)
    }
}