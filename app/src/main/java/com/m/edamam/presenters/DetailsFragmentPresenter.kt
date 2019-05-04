package com.m.edamam.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView
import kotlinx.coroutines.*

@InjectViewState
open class DetailsFragmentPresenter(private val repository: RecipeRepository)
    : MvpPresenter<DetailsFragmentView>() {

    var getRecipeByIdJob: Job? = null

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadRecipeDetails()
    }

    @SuppressLint("CheckResult")
    fun getRecipeDetails(id: String) {
        /*repository.getRecipeById(id)
                .subscribe(
                        {
                            it?.let { it1 -> viewState.showRecipeDetails(it1) }
                        },
                        {
                            it.printStackTrace()
                        }
                )*/

        getRecipeByIdJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                val recipe = repository.getRecipeById(id)
                withContext(Dispatchers.Main) {
                    recipe?.let { viewState.showRecipeDetails(it) }
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        getRecipeByIdJob?.cancel()
        super.onDestroy()
    }
}
