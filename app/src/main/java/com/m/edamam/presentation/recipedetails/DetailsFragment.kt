package com.m.edamam.presentation.recipedetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.m.edamam.App
import com.m.edamam.R
import com.m.edamam.constants.ARG_RECIPE
import com.m.edamam.data.pojo.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : MvpAppCompatFragment(), DetailsFragmentView {

    private var recipe: Recipe? = null

    @InjectPresenter
    lateinit var presenter: DetailsFragmentPresenter

    @ProvidePresenter
    fun initPresenter(): DetailsFragmentPresenter {
        return App.presenterComponent.getDetailsFragmentPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance(recipe: Recipe) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RECIPE, recipe)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        arguments?.let {
            recipe = it.getSerializable(ARG_RECIPE) as Recipe
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showRecipeDetails() {
        recipe?.let { setRecipe(it) }
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun setRecipe(recipe: Recipe) {
        view?.txt_label?.text = recipe.label
        view?.txt_ingredients?.text = recipe.ingredientLines.toString().drop(1).dropLast(1)
        Picasso.get()
            .load(recipe.image)
            .into(view?.img_recipe_details)
    }

    private fun initToolbar() {
        val appCompActivity = activity as AppCompatActivity
        appCompActivity.setSupportActionBar(toolbar_details as Toolbar)
        appCompActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompActivity.supportActionBar?.setDisplayShowHomeEnabled(true)

        (toolbar_details as Toolbar).setNavigationOnClickListener {
            presenter.onBackPressed()
        }
    }
}
