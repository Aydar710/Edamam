package com.m.edamam.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.m.edamam.R
import com.m.edamam.RecipeListAdapter
import com.m.edamam.constants.DEFAULT_PAGINATION_SIZE
import com.m.edamam.constants.SPREF_PAG_SIZE
import com.m.edamam.constants.TOTAL_ITEM_COUNT_MORE_THAN
import com.m.edamam.di.component.PresenterComponent
import com.m.edamam.di.component.DaggerPresenterComponent
import com.m.edamam.di.module.NetModule
import com.m.edamam.di.module.PresenterModule
import com.m.edamam.di.module.ServiceModule
import com.m.edamam.pojo.Hit
import com.m.edamam.presenters.RecipeListFragmentPresenter
import com.m.edamam.views.RecipeListFragmentView
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*

class RecipeListFragment : MvpAppCompatFragment(), RecipeListFragmentView, MainActivity.OnQueryTextListener {

    @InjectPresenter
    lateinit var presenter: RecipeListFragmentPresenter

    @ProvidePresenter
    fun initPresenter(): RecipeListFragmentPresenter {
        val component: PresenterComponent = DaggerPresenterComponent.create()
        return component.getRecipeListFragmentPresenter()
    }

    var queryText: String? = null
    var sPref: SharedPreferences? = null
    var adapter: RecipeListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        val rv = view.recycler_recipes
        val manager = LinearLayoutManager(activity)
        rv.layoutManager = manager
        adapter = RecipeListAdapter()
        adapter?.listItemClickListener = activity as MainActivity
        rv.adapter = adapter

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var currentPage: Int = 0
            private var isLastPage = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.getChildCount()
                val totalItemCount = manager.getItemCount()
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= TOTAL_ITEM_COUNT_MORE_THAN)

                        getPaginationSizeFromPreferences()
                                ?.let {
                                    presenter.loadNextElements(++currentPage,
                                            queryText.toString(), it)
                                }
                }
            }
        })
        return view
    }

    override fun updateAdapterByQueryResult(query: String) {
        presenter.updateAdapter(query)
    }

    override fun submitListIntoAdapter(list: List<Hit>) {
        adapter?.submitList(list as MutableList<Hit>)
    }

    override fun addElementsToAdapter(list: List<Hit>) {
        val hitList: ArrayList<Hit> = ArrayList()
        adapter?.getList()?.let { hitList.addAll(it) }
        hitList.addAll(list)
        adapter?.submitList(hitList)
    }

    override fun onQueryTextChanged(query: String) {
        Log.i("Tag", query)
        queryText = query
        updateAdapterByQueryResult(query)
    }

    override fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    fun getPaginationSizeFromPreferences(): Int? {
        activity?.let {
            sPref = it.getPreferences(Context.MODE_PRIVATE)
        }
        return sPref?.getInt(SPREF_PAG_SIZE, DEFAULT_PAGINATION_SIZE)
    }
}

