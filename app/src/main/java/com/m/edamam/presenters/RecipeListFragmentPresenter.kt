package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.RecipeListFragmentView
import kotlinx.coroutines.*

@InjectViewState
open class RecipeListFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<RecipeListFragmentView>() {

    private var getRecipeByNameJob: Job? = null
    private var loadNextElementsJob: Job? = null

    @SuppressLint("CheckResult")
    fun updateAdapter(query: String) {
        getRecipeByNameJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipes = repository.getRecipesByName(query)
                withContext(Dispatchers.Main) {
                    viewState.addElementsToAdapter(recipes)
                }
            } catch (e: Exception) {
                viewState.showError("Произошла ошибка при загрузке данных")
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("CheckResult")
    fun loadNextElements(currentPage: Int, query: String, pagSise: Int) {
        loadNextElementsJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipes = repository
                        .getRecipesByName(query = query, currentPage = currentPage, pagSize = pagSise)
                withContext(Dispatchers.Main) {
                    viewState.addElementsToAdapter(recipes)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        getRecipeByNameJob?.cancel()
        loadNextElementsJob?.cancel()
        super.onDestroy()
    }
}
