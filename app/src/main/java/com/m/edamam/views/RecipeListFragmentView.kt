package com.m.edamam.views

import com.m.edamam.pojo.Hit

interface RecipeListFragmentView {
    fun updateAdapterByQueryResult(query : String)
    fun submitListIntoAdapter(list :  List<Hit>)
}