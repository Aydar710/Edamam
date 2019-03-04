package com.m.edamam.views

import com.arellomobile.mvp.MvpView
import com.m.edamam.pojo.Hit

interface RecipeListFragmentView : MvpView {
    fun updateAdapterByQueryResult(query: String)
    fun submitListIntoAdapter(list: List<Hit>)
    fun addElementsToAdapter(list: List<Hit>)
    //fun onItemClicked(recipe : Recipe)
}
