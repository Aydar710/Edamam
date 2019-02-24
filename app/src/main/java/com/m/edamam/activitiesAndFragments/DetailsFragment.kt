package com.m.edamam.activitiesAndFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.m.edamam.R
import com.m.edamam.Retrofit
import com.m.edamam.pojo.Recipe
import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*

private const val RECIPE_ID = ""

class DetailsFragment : Fragment(), DetailsFragmentView {

    private lateinit var id: String
    private var api: EdamamApi = Retrofit.instance.getEdamamService()
    private var recipeRepository: RecipeRepository = RecipeRepository(api)
    lateinit var presenter: DetailsFragmentPresenter

    override fun loadRecipeDetails() {
        presenter.getRecipeDetails(id)
    }

    override fun showRecipeDetails(recipe: Recipe) {
        setRecipe(recipe)
    }

    private fun setRecipe(recipe: Recipe) {
        view?.tvLabel?.text = recipe.label
        view?.tvIngredients?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = DetailsFragmentPresenter(this)
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        arguments?.let {
            id = it.getString(RECIPE_ID)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecipeDetails()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id of recipe
         * @return A new instance of fragment DetailsFragment.
         */

        @JvmStatic
        fun newInstance(id: String) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(RECIPE_ID, id)
                    }
                }
    }
}
