package com.m.edamam.presentation.recipelist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.m.edamam.App
import com.m.edamam.R
import com.m.edamam.RecipeListAdapter
import com.m.edamam.constants.DEFAULT_PAGINATION_SIZE
import com.m.edamam.constants.SPREF_PAG_SIZE
import com.m.edamam.constants.TOTAL_ITEM_COUNT_MORE_THAN
import com.m.edamam.data.pojo.Hit
import com.m.edamam.data.pojo.Recipe
import com.m.edamam.di.component.DaggerAdapterComponent
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class RecipeListFragment :
    MvpAppCompatFragment(), RecipeListFragmentView,
    RecipeListAdapter.ListItemClickListener {

    @InjectPresenter
    @Inject
    lateinit var presenter: RecipeListFragmentPresenter

    @ProvidePresenter
    fun initPresenter(): RecipeListFragmentPresenter =
        App.presenterComponent.getRecipeListFragmentPresenter()

    var sPref: SharedPreferences? = null
    var adapter: RecipeListAdapter? = null
    var queryText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        val rv = view.recycler_recipes
        val manager = GridLayoutManager(activity, 2)
        rv.layoutManager = manager
        adapter = DaggerAdapterComponent.create().getRecipeListAdapter()
        adapter?.listItemClickListener = this
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_recipe_list_menu, menu)
        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = "Search recipe"

        //TODO перепистаь под RX
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) return true
                onQueryTextChanged(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) return true
                onQueryTextChanged(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
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

    override fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    override fun onClick(recipe: Recipe) {
        presenter.moveToRecipeDetailsScreen(recipe)
    }

    override fun showProgress() {
        pb_list.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_list.visibility = View.GONE
    }

    fun onQueryTextChanged(query: String) {
        Log.i("Tag", query)
        queryText = query
        updateAdapterByQueryResult(query)
    }

    fun getPaginationSizeFromPreferences(): Int? {
        activity?.let {
            sPref = it.getPreferences(Context.MODE_PRIVATE)
        }
        return sPref?.getInt(SPREF_PAG_SIZE, DEFAULT_PAGINATION_SIZE)
    }
}
