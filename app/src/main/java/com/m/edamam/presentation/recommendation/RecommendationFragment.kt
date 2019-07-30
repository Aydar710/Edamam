package com.m.edamam.presentation.recommendation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.m.edamam.App
import com.m.edamam.R
import com.m.edamam.data.pojo.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recommendation.*
import kotlinx.android.synthetic.main.fragment_recommendation.view.*
import kotlinx.android.synthetic.main.toolbar.*

class RecommendationFragment : MvpAppCompatFragment(), RecommendationFragmentView {

    private var id: String? = null

    @InjectPresenter
    lateinit var presenter: RecommendationFragmentPresenter

    @ProvidePresenter
    fun initPresenter(): RecommendationFragmentPresenter {
        return App.presenterComponent.getRecommendationFragmentPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        super.onViewCreated(view, savedInstanceState)
        getRecommendedRecipe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.recommendation_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_recipes -> presenter.moveToRecipeListScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getRecommendedRecipe() {
        presenter.getRecommendedRecipe()
    }

    override fun showRecommendedRecipe(recipe: Recipe) {
        view?.txt_label_rec?.text = recipe.label
        view?.txt_ingredients_rec?.text = recipe.ingredientLines.toString().drop(1).dropLast(1)
        Picasso.get()
            .load(recipe.image)
            .into(view?.img_recipe_details_rec)
    }

    override fun showProgress() {
        pb_recommendation.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_recommendation.visibility = View.GONE
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }
}
