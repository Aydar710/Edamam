package com.m.edamam.activitiesAndFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.m.edamam.R
import com.m.edamam.constants.SPREF_PAG_SIZE
import com.m.edamam.pojo.Hit
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.views.RecipeListFragmentView
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*


class RecipeListFragment : MvpAppCompatFragment(), RecipeListFragmentView, MainActivity.OnQueryTextListener {

    @InjectPresenter
    var presenter: RecipeListFragmentPresenter? = null
    var manager: LinearLayoutManager? = null
    var queryText: String? = null
    var sPref: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)


        var rv = view.recycler_recipes
        manager = LinearLayoutManager(activity)
        rv.layoutManager = manager
        rv.adapter = presenter?.adapter
        presenter?.adapter?.listItemClickListener = activity as MainActivity

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var currentPage: Int = 0
            private var isLastPage = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager?.getChildCount()
                val totalItemCount = manager?.getItemCount()
                val firstVisibleItemPosition = manager?.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if (firstVisibleItemPosition != null && totalItemCount != null
                            && visibleItemCount != null) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= 10)

                            getPaginationSizeFromPreferences()?.let { presenter?.loadNextElements(++currentPage, queryText.toString(), it) }
                    }
                }
            }
        })
        return view
    }

    override fun updateAdapterByQueryResult(query: String) {
        presenter?.updateAdapter(query)
    }

    override fun submitListIntoAdapter(list: List<Hit>) {
        presenter?.adapter?.submitList(list)
    }

    override fun addElementsToAdapter(list: List<Hit>) {
        var hitList: ArrayList<Hit> = ArrayList()
        presenter?.adapter?.getList()?.let { hitList.addAll(it) }
        hitList.addAll(list)
        presenter?.adapter?.submitList(hitList)
    }

    override fun onQueryTextChanged(query: String) {
        Log.i("Tag", query)
        queryText = query
        updateAdapterByQueryResult(query)
    }

    fun getPaginationSizeFromPreferences(): Int? {
        activity?.let {
            sPref = it.getPreferences(Context.MODE_PRIVATE)
        }
        return sPref?.getInt(SPREF_PAG_SIZE, 10)
    }
}