package com.m.edamam.activitiesAndFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.m.edamam.R
import com.m.edamam.Retrofit
import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_details.*

private const val RECIPE_ID = "id"

class DetailsFragment : Fragment() {

    private var id: String? = null
    private var api: EdamamApi = Retrofit.instance.getEdamamService()
    private var recipeRepository: RecipeRepository = RecipeRepository(api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(RECIPE_ID)
        }
        val recipe = recipeRepository.getRecipeById(id)
        setRecipe(recipe)
    }

    private fun setRecipe(recipe: Single<Recipe>?) {
        tvLabel.text = recipe?.subscribe{it -> it.label}.toString()
        tvIngredients.text = recipe?.subscribe{it -> it.ingredientLines}.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
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
