package com.m.edamam.presentation.recipelist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.m.edamam.data.pojo.Hit

@StateStrategyType(AddToEndSingleStrategy::class)
interface RecipeListFragmentView : MvpView {
    fun updateAdapterByQueryResult(query: String)
    fun submitListIntoAdapter(list: List<Hit>)
    fun addElementsToAdapter(list: List<Hit>)
    fun showError(error: String)
    fun showProgress()
    fun hideProgress()
    //fun onItemClicked(recipe : Recipe)
}
