package com.m.edamam.views

import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe

interface RecipeListFragmentView {
    fun updateAdapterByQueryResult(query : String)
    fun submitListIntoAdapter(list :  List<Hit>)
    //fun onItemClicked(recipe : Recipe)
}
