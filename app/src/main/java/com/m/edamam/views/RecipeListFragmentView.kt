package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.m.edamam.pojo.Hit

@StateStrategyType(AddToEndSingleStrategy::class)
interface RecipeListFragmentView : MvpView {
    fun updateAdapterByQueryResult(query: String)
    fun submitListIntoAdapter(list: List<Hit>)
    fun addElementsToAdapter(list: List<Hit>)
    fun showError(error : String)
    //fun onItemClicked(recipe : Recipe)
}
