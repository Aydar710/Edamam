package com.m.edamam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import com.m.edamam.R
import com.m.edamam.Retrofit
import com.m.edamam.constants.ARG_RECIPE_ID
import com.m.edamam.pojo.Recipe
import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : MvpAppCompatFragment(), DetailsFragmentView {

    private  var id: String? = null
    private var api: EdamamApi = Retrofit.instance.getEdamamService()
    private var recipeRepository: RecipeRepository = RecipeRepository(api)

    @InjectPresenter
    lateinit var presenter: DetailsFragmentPresenter

    @ProvidePresenter
    fun initPresenter() : DetailsFragmentPresenter =
            DetailsFragmentPresenter(RecipeRepository(Retrofit.instance.getEdamamService()))

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_RECIPE_ID, id)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        arguments?.let {
            id = it.getString(ARG_RECIPE_ID)
        }
        return view
    }

    override fun loadRecipeDetails() {
        id?.let { presenter.getRecipeDetails(it) }
    }

    override fun showRecipeDetails(recipe: Recipe) {
        setRecipe(recipe)
    }

    private fun setRecipe(recipe: Recipe) {
        view?.txt_label?.text = recipe.label
        view?.txt_ingredients?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details)
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }
}
