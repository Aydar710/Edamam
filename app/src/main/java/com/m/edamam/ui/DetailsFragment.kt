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
import com.m.edamam.constants.ARG_RECIPE
import com.m.edamam.di.component.DaggerPresenterComponent
import com.m.edamam.di.component.PresenterComponent
import com.m.edamam.di.module.AppModule
import com.m.edamam.pojo.Recipe
import com.m.edamam.presenters.DetailsFragmentPresenter
import com.m.edamam.views.DetailsFragmentView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : MvpAppCompatFragment(), DetailsFragmentView {

    private var recipe : Recipe? = null

    @InjectPresenter
    lateinit var presenter: DetailsFragmentPresenter

    @ProvidePresenter
    fun initPresenter() : DetailsFragmentPresenter {
        val component : PresenterComponent = DaggerPresenterComponent.builder()
                .appModule(activity?.let { AppModule(it) })
                .build()
        return component.getDetailsFragmentPresenter()
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
            print("asd")
        }
        return view
    }

    override fun showRecipeDetails() {
        recipe?.let { setRecipe(it) }
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun setRecipe(recipe: Recipe) {
        view?.txt_label?.text = recipe.label
        view?.txt_ingredients?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details)
    }
}
