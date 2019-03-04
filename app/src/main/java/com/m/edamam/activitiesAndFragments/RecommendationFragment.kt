package com.m.edamam.activitiesAndFragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import android.support.v4.content.ContextCompat.getSystemService


class RecommendationFragment : MvpAppCompatFragment(), RecommendationFragmentView {

    private lateinit var id: String
    private var api: EdamamApi = Retrofit.instance.getEdamamService()
    private var recipeRepository: RecipeRepository = RecipeRepository(api)

    @InjectPresenter
    lateinit var presenter: RecommendationFragmentPresenter

    companion object {
        @JvmStatic
        fun newInstance() =
                RecommendationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)
        /*arguments?.let {
            id = it.getString(ARG_RECIPE_ID)
        }*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRecommendedRecipe()
    }

    override fun getRecommendedRecipe() {
        /*if (isOnline()) {
            presenter.getRecommendedRecipe()
        }else{
            presenter.getRecommendedRecipeFromDB()
        }*/
        presenter.getRecommendedRecipe()
    }

    override fun showRecommendedRecipe(recipe: Recipe) {
        view?.txt_label_rec?.text = recipe.label
        view?.txt_ingredients_rec?.text = recipe.ingredientLines.toString()
        Picasso.get()
                .load(recipe.image)
                .into(view?.img_recipe_details_rec)
    }

    fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

    fun isOnline(): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}