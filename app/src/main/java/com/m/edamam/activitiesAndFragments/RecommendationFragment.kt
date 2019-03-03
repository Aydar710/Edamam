package com.m.edamam.activitiesAndFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.m.edamam.R
import com.m.edamam.Retrofit
import com.m.edamam.constants.ARG_RECIPE_ID
import com.m.edamam.pojo.Recipe
import com.m.edamam.presenters.RecommendationFragmentPresenter
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecommendationFragmentView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recommendation.view.*

class RecommendationFragment : MvpAppCompatFragment(), RecommendationFragmentView {

    private lateinit var id: String
    private var api: EdamamApi = Retrofit.instance.getEdamamService()
    private var recipeRepository: RecipeRepository = RecipeRepository(api)

    @InjectPresenter
    lateinit var presenter: RecommendationFragmentPresenter

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_RECIPE_ID, id)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)
        arguments?.let {
            id = it.getString(ARG_RECIPE_ID)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRecommendedRecipe()
    }

    override fun getRecommendedRecipe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecommendedRecipe(recipe: Recipe) {
        /*view?.tx
        view?.txt_ingredients?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details)*/
    }

    private fun setRecipe(recipe: Recipe) {
        view?.txt_label_rec?.text = recipe.label
        view?.txt_ingredients_rec?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details_rec)
    }
}